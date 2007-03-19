package org.omnetpp.inifile.editor.text.highlight;


import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.RuleBasedScanner;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.jface.text.rules.WordRule;
import org.omnetpp.inifile.editor.text.NedHelper;

/**
 * A rule based PrivatedNedDoc scanner.
 */
//XXX TODO rename, revise, possibly remove...
public class NedPrivateDocColorizerScanner extends RuleBasedScanner {

    /**
	 * Create a new neddoc scanner for the given color provider.
	 * 
	 * @param provider the color provider
	 */
	 public NedPrivateDocColorizerScanner() {
		super();
        // this is the default token for a comment
        setDefaultReturnToken(NedHelper.docPrivateDefaultToken);

		List<IRule> list= new ArrayList<IRule>();
        
        // Add word rule for private comment hints
        WordRule hintRule= new WordRule(NedHelper.inifileWordDetector, Token.UNDEFINED);
        for (int i= 0; i < NedHelper.highlightPrivateDocTodo.length; i++)
            hintRule.addWord(NedHelper.highlightPrivateDocTodo[i], NedHelper.docPrivateTodoToken);
        list.add(hintRule);

		IRule[] result= new IRule[list.size()];
		list.toArray(result);
		setRules(result);
	}
}
