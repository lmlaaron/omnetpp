package org.omnetpp.test.gui.inifileeditor;

import org.omnetpp.common.ui.GenericTreeNode;
import org.omnetpp.test.gui.access.InifileEditorAccess;
import org.omnetpp.test.gui.access.InifileFormEditorAccess;

import com.simulcraft.test.gui.access.CompositeAccess;
import com.simulcraft.test.gui.access.TreeAccess;

public class SectionsTest extends InifileEditorTestCase {
    //TODO finish

    private void prepareTest(String content) throws Exception {
        createFileWithContent(content);
        openFileFromProjectExplorerView();
    }

    private static GenericTreeNode[] toArray(GenericTreeNode... trees) {
        return trees;
    }

    private static GenericTreeNode nw(String containedWord, GenericTreeNode... children) {
        return n(".*\\b" + containedWord + "\\b.*", children);
    }

    private static GenericTreeNode n(String labelRegex, GenericTreeNode... children) {
        GenericTreeNode node = new GenericTreeNode(labelRegex);
        for (GenericTreeNode child : children)
            node.addChild(child);
        return node;
    }

    private void checkSectionsTreeViewContent(String inifileContent, GenericTreeNode[] trees) throws Exception {
        prepareTest(inifileContent);

        InifileEditorAccess inifileEditor = findInifileEditor();
        InifileFormEditorAccess formEditor = inifileEditor.ensureActiveFormEditor();
        CompositeAccess sectionsPage = formEditor.activateCategoryPage("Sections");
        TreeAccess sectionsTree = sectionsPage.findTree();
        sectionsTree.assertContent(trees);
    }

    public void testEmptyInifile() throws Exception {
        checkSectionsTreeViewContent("", 
                toArray(
                        n("General")));  // still displays "General" with a hollow icon
    }

    public void testGeneralSectionOnly() throws Exception {
        checkSectionsTreeViewContent(
                "[General]\n", 
                toArray(
                        n("General")));
    }

    public void testGeneralSectionOnly2() throws Exception {
        // test that the network is displayed in the label 
        checkSectionsTreeViewContent(
                "[General]\n" +
                "network = TestNetwork\n", 
                toArray(
                        n("General.*TestNetwork.*")));
    }

    public void testManySectionsWithGeneral() throws Exception {
        checkSectionsTreeViewContent(
                "[General]\n" + 
                "[Config Apple]\n" + 
                "[Config GreenApple]\n" +
                "extends = Apple\n" +
                "[Config RedApple]\n" +
                "extends = Apple\n" +
                "[Config Orange]\n" +
                "[Config SmallOrange]\n" + 
                "extends = Orange\n" +
                "[Config SmallGreenOrange]\n" + 
                "extends = SmallOrange\n" +
                "[Config BigOrange]\n" +
                "extends = Orange\n" +
                "[Config Banana]\n",
                toArray(
                        nw("General", 
                                nw("Apple", 
                                        nw("GreenApple"),
                                        nw("RedApple")),
                                nw("Orange",
                                        nw("SmallOrange",
                                            nw("SmallGreenOrange")),
                                        nw("BigOrange")),
                                nw("Banana"))));
    }

    //TODO:
    //   many sections
    //   cycle in section inheritance
    //   nonexistent base section
    // in the above tests: check that sections appear on the Parameters page as well
    //  check label (inculdes network, descriotionm etc)
    //  network inheritance
    //  extends=General yes/no, general section yes/no
    //  General extends something (bogus)

    // TODO:
    //   create, edit section by dialog
    //   delete section
    //   move by drag&drop
    //   copy/paste (if works)

    // TODO:
    //  check tooltip, after F2

}
