/*
 * $Id: DropConstraintCommand.java,v 1.8 2003/05/12 22:22:35 rwald Exp $
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

package org.axiondb.engine.commands;

import org.axiondb.AxionException;
import org.axiondb.Database;
import org.axiondb.Table;
import org.axiondb.jdbc.AxionResultSet;

/**
 * A <code>DROP CONSTRAINT</code> command.
 * 
 * @version $Revision: 1.8 $ $Date: 2003/05/12 22:22:35 $
 * @author Rodney Waldhoff
 */
public class DropConstraintCommand extends BaseAxionCommand {
    public DropConstraintCommand(String tableName, String constraintName) {
        _tableName = tableName;
        _constraintName = constraintName;
    }

    public String getConstraintName() {
        return _constraintName;
    }

    public void setConstraintName(String name) {
        _constraintName = name;
    }

    public void setTableName(String name) {
        _tableName = name;
    }

    public String getTableName() {
        return _tableName;
    }

    public boolean execute(Database db) throws AxionException {
        assertNotReadOnly(db);
        if(null == _tableName) {
            throw new AxionException("Table name must not be null.");
        }
        if(null == _constraintName) {
            throw new AxionException("Constraint name must not be null.");
        }
        Table table = db.getTable(_tableName);
        if(null == table) {
            throw new AxionException("Table " + _tableName + " not found.");
        } else {
            table.removeConstraint(_constraintName);
            db.tableAltered(table);
        }
        return false;
    }

    /** Unsupported */
    public AxionResultSet executeQuery(Database database) throws AxionException {
        throw new UnsupportedOperationException("Use execute.");
    }

    public int executeUpdate(Database database) throws AxionException {
        execute(database);
        return 0;
    }

    public String toString() {
        StringBuffer buf = new StringBuffer();
        buf.append("ALTER TABLE ");
        buf.append(_tableName);
        buf.append(" DROP CONSTRAINT ");
        buf.append(_constraintName);
        return buf.toString();
    }

    private String _tableName = null;
    private String _constraintName = null;
}