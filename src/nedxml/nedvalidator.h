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

#ifndef __NEDVALIDATOR_H
#define __NEDVALIDATOR_H

#include "nedelements.h"

/**
 * GENERATED CLASS. Abtract base class for NED validators.
 * 
 * @ingroup Validation
 */
class NEDValidatorBase
{
  public:
    NEDValidatorBase() {}
    virtual ~NEDValidatorBase() {}

    virtual void validate(NEDElement *node); // recursive
    virtual void validateElement(NEDElement *node);

    virtual void validateElement(NedFilesNode *node) = 0;
    virtual void validateElement(NedFileNode *node) = 0;
    virtual void validateElement(ImportNode *node) = 0;
    virtual void validateElement(ImportedFileNode *node) = 0;
    virtual void validateElement(ChannelNode *node) = 0;
    virtual void validateElement(ChannelAttrNode *node) = 0;
    virtual void validateElement(NetworkNode *node) = 0;
    virtual void validateElement(SimpleModuleNode *node) = 0;
    virtual void validateElement(CompoundModuleNode *node) = 0;
    virtual void validateElement(ParamsNode *node) = 0;
    virtual void validateElement(ParamNode *node) = 0;
    virtual void validateElement(GatesNode *node) = 0;
    virtual void validateElement(GateNode *node) = 0;
    virtual void validateElement(MachinesNode *node) = 0;
    virtual void validateElement(MachineNode *node) = 0;
    virtual void validateElement(SubmodulesNode *node) = 0;
    virtual void validateElement(SubmoduleNode *node) = 0;
    virtual void validateElement(SubstparamsNode *node) = 0;
    virtual void validateElement(SubstparamNode *node) = 0;
    virtual void validateElement(GatesizesNode *node) = 0;
    virtual void validateElement(GatesizeNode *node) = 0;
    virtual void validateElement(SubstmachinesNode *node) = 0;
    virtual void validateElement(SubstmachineNode *node) = 0;
    virtual void validateElement(ConnectionsNode *node) = 0;
    virtual void validateElement(ConnectionNode *node) = 0;
    virtual void validateElement(ConnAttrNode *node) = 0;
    virtual void validateElement(ForLoopNode *node) = 0;
    virtual void validateElement(LoopVarNode *node) = 0;
    virtual void validateElement(DisplayStringNode *node) = 0;
    virtual void validateElement(ExpressionNode *node) = 0;
    virtual void validateElement(OperatorNode *node) = 0;
    virtual void validateElement(FunctionNode *node) = 0;
    virtual void validateElement(ParamRefNode *node) = 0;
    virtual void validateElement(IdentNode *node) = 0;
    virtual void validateElement(ConstNode *node) = 0;
    virtual void validateElement(UnknownNode *node) = 0;
};

#endif

