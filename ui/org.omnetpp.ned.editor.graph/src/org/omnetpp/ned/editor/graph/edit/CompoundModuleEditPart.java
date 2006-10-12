package org.omnetpp.ned.editor.graph.edit;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.AutoexposeHelper;
import org.eclipse.gef.CompoundSnapToHelper;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.ExposeHelper;
import org.eclipse.gef.MouseWheelHelper;
import org.eclipse.gef.SnapToGeometry;
import org.eclipse.gef.SnapToHelper;
import org.eclipse.gef.editparts.ViewportAutoexposeHelper;
import org.eclipse.gef.editparts.ViewportExposeHelper;
import org.eclipse.gef.editparts.ViewportMouseWheelHelper;
import org.eclipse.gef.editpolicies.SnapFeedbackPolicy;
import org.omnetpp.common.displaymodel.DisplayString;
import org.omnetpp.common.displaymodel.IDisplayString;
import org.omnetpp.common.displaymodel.IDisplayString.Prop;
import org.omnetpp.figures.CompoundModuleFigure;
import org.omnetpp.figures.CompoundModuleGateAnchor;
import org.omnetpp.figures.GateAnchor;
import org.omnetpp.ned.editor.graph.edit.policies.CompoundModuleLayoutEditPolicy;
import org.omnetpp.ned2.model.CompoundModuleNodeEx;
import org.omnetpp.ned2.model.INamedGraphNode;
import org.omnetpp.ned2.model.NEDElement;
import org.omnetpp.ned2.model.SubmoduleNodeEx;
import org.omnetpp.resources.NEDResourcesPlugin;

public class CompoundModuleEditPart extends ModuleEditPart {


    @Override
    protected void createEditPolicies() {
        super.createEditPolicies();
        installEditPolicy(EditPolicy.LAYOUT_ROLE, new CompoundModuleLayoutEditPolicy((XYLayout) getContentPane()
                .getLayoutManager()));
        installEditPolicy("Snap Feedback", new SnapFeedbackPolicy()); //$NON-NLS-1$
    }

    /**
     * Creates a new Module Figure and returns it.
     * 
     * @return Figure representing the module.
     */
    @Override
    protected IFigure createFigure() {
        return new CompoundModuleFigure();
    }

    /**
     * Returns the Figure of this as a ModuleFigure.
     * 
     * @return ModuleFigure of this.
     */
    protected CompoundModuleFigure getCompoundModuleFigure() {
        return (CompoundModuleFigure) getFigure();
    }

    @Override
    public IFigure getContentPane() {
        return getCompoundModuleFigure().getContentsPane();
    }

    @Override
    public Object getAdapter(Class key) {
        
        if (key == AutoexposeHelper.class) return new ViewportAutoexposeHelper(this);
        
        if (key == ExposeHelper.class) return new ViewportExposeHelper(this);

        if (key == MouseWheelHelper.class) return new ViewportMouseWheelHelper(this);

        // snap to grig/guide adaptor
        if (key == SnapToHelper.class) {
            List snapStrategies = new ArrayList();
            Boolean val = (Boolean) getViewer().getProperty(SnapToGeometry.PROPERTY_SNAP_ENABLED);
            if (val != null && val.booleanValue()) snapStrategies.add(new SnapToGeometry(this));

            if (snapStrategies.size() == 0) return null;
            if (snapStrategies.size() == 1) return snapStrategies.get(0);

            SnapToHelper ss[] = new SnapToHelper[snapStrategies.size()];
            for (int i = 0; i < snapStrategies.size(); i++)
                ss[i] = (SnapToHelper) snapStrategies.get(i);
            return new CompoundSnapToHelper(ss);
        }
        
        return super.getAdapter(key);
    }

    @Override
    protected List getModelChildren() {
        // define the properties that determine the visual appearence
    	CompoundModuleNodeEx compModule = (CompoundModuleNodeEx)getNEDModel();
    	List<SubmoduleNodeEx> smList = compModule.getOwnSubmodules();

    	// if it's an derived compound module add the inherited submodules to the list
    	
    	if (compModule.getFirstExtendsChild() != null) {
    		smList.addAll(NEDResourcesPlugin.getNEDResources()
        					.getAllSubmodules(compModule.getFirstExtendsChild().getName()));
    	}
    	return smList;
    }
    
    @Override
    public void attributeChanged(NEDElement node, String attr) {
    	super.attributeChanged(node, attr);
    	// NEDED only if the scaling property has changed
   		refreshChildrenVisuals();
    	// refresh only ourselves
    	refreshVisuals();
    }
    
	/**
     * Updates the visual aspect of this.
     */
    @Override
    protected void refreshVisuals() {
        
        // define the properties that determine the visual appearence
    	CompoundModuleNodeEx compModule = (CompoundModuleNodeEx)getNEDModel();
    	
        getCompoundModuleFigure().setName(compModule.getName());

        // parse a dispaly string, so it's easier to get values from it.
    	DisplayString dps = compModule.getDisplayString();
        // get a fallback display string for the module type
    	// the effective display string of its first ancestor (if any)
    	if (compModule.getFirstExtendsChild() != null) {
    		DisplayString fallbackDps = NEDResourcesPlugin.getNEDResources()
        								.getEffectiveDisplayString(compModule.getFirstExtendsChild().getName());
        	dps.setDefaults(fallbackDps);
    	}
    	getCompoundModuleFigure().setDisplayString(dps);
        
    }

	/**
	 * Returns whether the compound module is selectable (mouse is over the borering area)
	 * for the slection tool based on the current mouse target coordinates.
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean isOnBorder(int x, int y) {
		return getCompoundModuleFigure().isOnBorder(x, y);
	}
	
	/**
	 * Compute the source connection anchor to be assigned based on the current mouse 
	 * location and available gates. 
	 * @param p current mouse coordinates
	 * @return The selected connection anchor
	 */
	public ConnectionAnchor getConnectionAnchorAt(Point p) {
		return new CompoundModuleGateAnchor(getFigure());
	}

	/**
	 * Returns a conn anchor registered for the given gate
	 * @param gate
	 * @return
	 */
	public GateAnchor getConnectionAnchor(String gate) {
		return new CompoundModuleGateAnchor(getFigure());
	}
    
    /**
     * @return The current scaling factor of the compound module
     */
    public float getScale() {
        return ((CompoundModuleNodeEx)getModel()).getDisplayString().getScale();
    }
}
