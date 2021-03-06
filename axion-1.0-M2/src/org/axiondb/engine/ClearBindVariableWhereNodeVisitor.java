/*
 * $Id: ClearBindVariableWhereNodeVisitor.java,v 1.3 2003/03/27 19:14:03 rwald Exp $
 * =======================================================================
 * Copyright (c) 2002 Axion Development Team.  All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 * 1. Redistributions of source code must retain the above
 *    copyright notice, this list of conditions and the following
 *    disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the
 *    distribution.
 *
 * 3. The names "Tigris", "Axion", nor the names of its contributors may
 *    not be used to endorse or promote products derived from this
 *    software without specific prior written permission.
 *
 * 4. Products derived from this software may not be called "Axion", nor
 *    may "Tigris" or "Axion" appear in their names without specific prior
 *    written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A
 * PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
 * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 * =======================================================================
 */

package org.axiondb.engine;

import org.axiondb.BindVariable;
import org.axiondb.LeafWhereNode;

/**
 * A {@link WhereNodeVisitor} that {@link BindVariable#clearBoundValue clears}
 * any {@link BindVariable}s found in the tree.
 * <p>
 * This visitor is completely stateless, and can be re-used or used in
 * parallel without any problem.
 * 
 * @version $Revision: 1.3 $ $Date: 2003/03/27 19:14:03 $
 * @author Rod Waldhoff
 */
public class ClearBindVariableWhereNodeVisitor extends BaseWhereNodeVisitor {
    public ClearBindVariableWhereNodeVisitor() {
    }
    
    public void visitLeafWhereNode(LeafWhereNode node) {
        if(node.getLeft() instanceof BindVariable) {
            ((BindVariable)(node.getLeft())).clearBoundValue();
        }
        if(node.getRight() instanceof BindVariable) {
            ((BindVariable)(node.getRight())).clearBoundValue();
        }
    }
}
