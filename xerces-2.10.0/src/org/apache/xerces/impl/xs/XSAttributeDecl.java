/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.xerces.impl.xs;

import org.apache.xerces.impl.dv.ValidatedInfo;
import org.apache.xerces.impl.dv.XSSimpleType;
import org.apache.xerces.impl.xs.util.XSObjectListImpl;
import org.apache.xerces.xni.QName;
import org.apache.xerces.xs.ShortList;
import org.apache.xerces.xs.XSAnnotation;
import org.apache.xerces.xs.XSAttributeDeclaration;
import org.apache.xerces.xs.XSComplexTypeDefinition;
import org.apache.xerces.xs.XSConstants;
import org.apache.xerces.xs.XSNamespaceItem;
import org.apache.xerces.xs.XSObjectList;
import org.apache.xerces.xs.XSSimpleTypeDefinition;

/**
 * The XML representation for an attribute declaration
 * schema component is an &lt;attribute&gt; element information item
 * 
 * @xerces.internal 
 *
 * @author Elena Litani, IBM
 * @author Sandy Gao, IBM
 * @version $Id: XSAttributeDecl.java 821931 2009-10-05 17:42:57Z sandygao $
 */
public class XSAttributeDecl implements XSAttributeDeclaration {

    // scopes
    public final static short     SCOPE_ABSENT        = 0;
    public final static short     SCOPE_GLOBAL        = 1;
    public final static short     SCOPE_LOCAL         = 2;

    // the name of the attribute
    String fName = null;
    // the target namespace of the attribute
    String fTargetNamespace = null;
    // the simple type of the attribute
    XSSimpleType fType = null;
    public QName fUnresolvedTypeName = null;
    // value constraint type: default, fixed or !specified
    short fConstraintType = XSConstants.VC_NONE;
    // scope
    short fScope = XSConstants.SCOPE_ABSENT;
    // enclosing complex type, when the scope is local
    XSComplexTypeDecl fEnclosingCT = null;
    // optional annotations
    XSObjectList fAnnotations = null;    
    // value constraint value
    ValidatedInfo fDefault = null;
    // The namespace schema information item corresponding to the target namespace 
    // of the attribute declaration, if it is globally declared; or null otherwise.
    private XSNamespaceItem fNamespaceItem = null;

    public void setValues(String name, String targetNamespace,
            XSSimpleType simpleType, short constraintType, short scope,
            ValidatedInfo valInfo, XSComplexTypeDecl enclosingCT,
            XSObjectList annotations) {
        fName = name;
        fTargetNamespace = targetNamespace;
        fType = simpleType;
        fConstraintType = constraintType;
        fScope = scope;
        fDefault = valInfo;
        fEnclosingCT = enclosingCT;
        fAnnotations = annotations;
    }

    public void reset(){
        fName = null;
        fTargetNamespace = null;
        fType = null;
        fUnresolvedTypeName = null;
        fConstraintType = XSConstants.VC_NONE;
        fScope = XSConstants.SCOPE_ABSENT;
        fDefault = null;
        fAnnotations = null;
    }

    /**
     * Get the type of the object, i.e ELEMENT_DECLARATION.
     */
    public short getType() {
        return XSConstants.ATTRIBUTE_DECLARATION;
    }

    /**
     * The <code>name</code> of this <code>XSObject</code> depending on the
     * <code>XSObject</code> type.
     */
    public String getName() {
        return fName;
    }

    /**
     * The namespace URI of this node, or <code>null</code> if it is
     * unspecified.  defines how a namespace URI is attached to schema
     * components.
     */
    public String getNamespace() {
        return fTargetNamespace;
    }

    /**
     * A simple type definition
     */
    public XSSimpleTypeDefinition getTypeDefinition() {
        return fType;
    }

    /**
     * Optional. Either global or a complex type definition (
     * <code>ctDefinition</code>). This property is absent in the case of
     * declarations within attribute group definitions: their scope will be
     * determined when they are used in the construction of complex type
     * definitions.
     */
    public short getScope() {
        return fScope;
    }

    /**
     * Locally scoped declarations are available for use only within the
     * complex type definition identified by the <code>scope</code>
     * property.
     */
    public XSComplexTypeDefinition getEnclosingCTDefinition() {
        return fEnclosingCT;
    }

    /**
     * Value constraint: one of default, fixed.
     */
    public short getConstraintType() {
        return fConstraintType;
    }

    /**
     * Value constraint: The actual value (with respect to the {type
     * definition}) Should we return Object instead of DOMString?
     */
    public String getConstraintValue() {
        // REVISIT: SCAPI: what's the proper representation
        return getConstraintType() == XSConstants.VC_NONE ?
               null :
               fDefault.stringValue();
    }

    /**
     * Optional. Annotation.
     */
    public XSAnnotation getAnnotation() {
        return (fAnnotations != null) ? (XSAnnotation) fAnnotations.item(0) : null;
    }

    /**
     * Optional. Annotations.
     */
    public XSObjectList getAnnotations() {
        return (fAnnotations != null) ? fAnnotations : XSObjectListImpl.EMPTY_LIST;
    }
    
    public ValidatedInfo getValInfo() {
        return fDefault;
    }
    
    /**
     * @see org.apache.xerces.xs.XSObject#getNamespaceItem()
     */
    public XSNamespaceItem getNamespaceItem() {
        return fNamespaceItem;
    }

    void setNamespaceItem(XSNamespaceItem namespaceItem) {
        fNamespaceItem = namespaceItem;
    }

    public Object getActualVC() {
        return getConstraintType() == XSConstants.VC_NONE ?
               null :
               fDefault.actualValue;
    }

    public short getActualVCType() {
        return getConstraintType() == XSConstants.VC_NONE ?
               XSConstants.UNAVAILABLE_DT :
               fDefault.actualValueType;
    }

    public ShortList getItemValueTypes() {
        return getConstraintType() == XSConstants.VC_NONE ?
               null :
               fDefault.itemValueTypes;
    }

} // class XSAttributeDecl
