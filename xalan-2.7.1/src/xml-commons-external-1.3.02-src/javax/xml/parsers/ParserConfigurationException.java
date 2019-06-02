
/*
 * Copyright 2003-2004 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

// $Id: ParserConfigurationException.java,v 1.3 2005/04/08 10:39:13 neeraj Exp $

package javax.xml.parsers;

/**
 * Indicates a serious configuration error.
 *
 * @author <a href="mailto:Jeff.Suttor@Sun.com">Jeff Suttor</a>
 * @version $Revision: 1.3 $, $Date: 2005/04/08 10:39:13 $
 */

public class ParserConfigurationException extends Exception {

    /**
     * Create a new <code>ParserConfigurationException</code> with no
     * detail mesage.
     */

    public ParserConfigurationException() {
        super();
    }

    /**
     * Create a new <code>ParserConfigurationException</code> with
     * the <code>String</code> specified as an error message.
     *
     * @param msg The error message for the exception.
     */
    
    public ParserConfigurationException(String msg) {
        super(msg);
    }

}

