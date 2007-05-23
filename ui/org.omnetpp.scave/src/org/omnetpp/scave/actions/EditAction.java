package org.omnetpp.scave.actions;

import static org.omnetpp.common.image.ImageFactory.TOOLBAR_IMAGE_PROPERTIES;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.omnetpp.common.image.ImageFactory;
import org.omnetpp.scave.editors.ScaveEditor;
import org.omnetpp.scave.editors.ui.EditDialog;

/**
 * Opens an edit dialog for the selected dataset, chart, chart sheet, etc.
 */
public class EditAction extends AbstractScaveAction {
	public EditAction() {
        setText("Properties...");
        setToolTipText("Edit the properties of the selected item");
		setImageDescriptor(ImageFactory.getDescriptor(TOOLBAR_IMAGE_PROPERTIES));
	}

	@Override
	protected void doRun(ScaveEditor scaveEditor, IStructuredSelection selection) {
		if (isApplicable(scaveEditor, selection)) {
			EObject object = (EObject)selection.getFirstElement(); //TODO edit several objects together?
			EditDialog dialog = new EditDialog(scaveEditor.getSite().getShell(), object, scaveEditor);
			EStructuralFeature[] features = dialog.getFeatures();
			if (features.length > 0)
				dialog.open();
		}
	}

	@Override
	public boolean isApplicable(ScaveEditor editor, IStructuredSelection selection) {
		return selection.size() == 1 && selection.getFirstElement() instanceof EObject &&
				((EObject)selection.getFirstElement()).eResource() != null &&
				EditDialog.getEditableFeatures((EObject)selection.getFirstElement(), editor).length > 0;
	}
}
