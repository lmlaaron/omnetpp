//==========================================================================
// Part of the OMNeT++ Discrete System Simulation System
//
// GENERATED FILE -- DO NOT EDIT!
//
//==========================================================================

/*--------------------------------------------------------------*
  Copyright (C) 2002 Andras Varga

  This file is distributed WITHOUT ANY WARRANTY. See the file
  `license' for details on this and other legal matters.
*--------------------------------------------------------------*/

// *** THIS IS A GENERATED FILE, HAND-EDITING IT IS USELESS! ***

#ifndef __DTDVALIDATOR_H
#define __DTDVALIDATOR_H

#include "nedelements.h"
#include "nedvalidator.h"

/**
 * GENERATED CLASS. Validates a NEDElement tree by the DTD.
 * 
 * @ingroup Validation
 */
class NEDDTDValidator : public NEDValidatorBase
{
  protected:
    void checkSequence(NEDElement *node, int tags[], char mult[], int n);
    void checkChoice(NEDElement *node, int tags[], int n, char mult);
    void checkEmpty(NEDElement *node);
    void checkRequiredAttribute(NEDElement *node, const char *attr);
    void checkEnumeratedAttribute(NEDElement *node, const char *attr, const char *vals[], int n);
    void checkNameAttribute(NEDElement *node, const char *attr);
    void checkCommentAttribute(NEDElement *node, const char *attr);
    void checkNMTokenAttribute(NEDElement *node, const char *attr);
  public:
    NEDDTDValidator() {}
    virtual ~NEDDTDValidator() {}

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

