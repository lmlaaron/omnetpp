//==========================================================================
// nedsemanticvalidator.h - part of the OMNeT++ Discrete System Simulation System
//
// Contents:
//   class NEDSemanticValidator
//
//==========================================================================

/*--------------------------------------------------------------*
  Copyright (C) 2002 Andras Varga

  This file is distributed WITHOUT ANY WARRANTY. See the file
  `license' for details on this and other legal matters.
*--------------------------------------------------------------*/


#ifndef __NEDSEMANTICVALIDATOR_H
#define __NEDSEMANTICVALIDATOR_H

#include <string.h>
#include "nedvalidator.h"

class NEDSymbolTable;

/**
 * Performs semantic validation in addition to DTD validation. FIXME more...
 *
 * @ingroup Validation
 */
class NEDSemanticValidator : public NEDValidatorBase
{
  protected:
    bool parsedExpressions;
    NEDSymbolTable *symboltable;

    // temporary variables:
    NEDElement *moduletypedecl;

    NEDElement *findChildWithTagAndAttribute(NEDElement *node, int tag, const char *attr, const char *value);

  public:
    NEDSemanticValidator(bool parsedExpr, NEDSymbolTable *symbtab);
    virtual ~NEDSemanticValidator();

    virtual void validateElement(NedFilesNode *node);
    virtual void validateElement(NedFileNode *node);
    virtual void validateElement(ImportNode *node);
    virtual void validateElement(ImportedFileNode *node);
    virtual void validateElement(ChannelNode *node);
    virtual void validateElement(ChannelAttrNode *node);
    virtual void validateElement(NetworkNode *node);
    virtual void validateElement(SimpleModuleNode *node);
    virtual void validateElement(CompoundModuleNode *node);
    virtual void validateElement(ParamsNode *node);
    virtual void validateElement(ParamNode *node);
    virtual void validateElement(GatesNode *node);
    virtual void validateElement(GateNode *node);
    virtual void validateElement(MachinesNode *node);
    virtual void validateElement(MachineNode *node);
    virtual void validateElement(SubmodulesNode *node);
    virtual void validateElement(SubmoduleNode *node);
    virtual void validateElement(SubstparamsNode *node);
    virtual void validateElement(SubstparamNode *node);
    virtual void validateElement(GatesizesNode *node);
    virtual void validateElement(GatesizeNode *node);
    virtual void validateElement(SubstmachinesNode *node);
    virtual void validateElement(SubstmachineNode *node);
    virtual void validateElement(ConnectionsNode *node);
    virtual void validateElement(ConnectionNode *node);
    virtual void validateElement(ConnAttrNode *node);
    virtual void validateElement(ForLoopNode *node);
    virtual void validateElement(LoopVarNode *node);
    virtual void validateElement(DisplayStringNode *node);
    virtual void validateElement(ExpressionNode *node);
    virtual void validateElement(OperatorNode *node);
    virtual void validateElement(FunctionNode *node);
    virtual void validateElement(ParamRefNode *node);
    virtual void validateElement(IdentNode *node);
    virtual void validateElement(ConstNode *node);
    virtual void validateElement(UnknownNode *node);
};

#endif

