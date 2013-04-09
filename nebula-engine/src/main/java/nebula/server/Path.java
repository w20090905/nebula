/*
 * Path.java February 2001
 *
 * Copyright (C) 2001, Niall Gallagher <niallg@users.sf.net>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or 
 * implied. See the License for the specific language governing 
 * permissions and limitations under the License.
 */
 
package nebula.server;

/**
 * The <code>Path</code> represents the path part of a URI. This provides 
 * the various components of the URI path to the user. The normalization
 * of the path is the conversion of the path given into it's actual path by
 * removing the references to the parent directories and to the current dir.
 * <p>
 * If the path that this represents is <code>/usr/bin/../etc/./README</code>
 * then the actual path, normalized, is <code>/usr/etc/README</code>. Once
 * the path has been normalized it is possible to acquire the segments as
 * an array of strings, which allows simple manipulation of the path.
 *
 * @author Niall Gallagher
 *
 * @see org.simpleframework.http.parse.PathParser
 */
public interface Path {
    
   /**
    * This will return the extension that the file name contains.
    * For example a file name <code>file.en_US.extension</code>
    * will produce an extension of <code>extension</code>. This 
    * will return null if the path contains no file extension.
    *
    * @return this will return the extension this path contains
    */   
   public String getExtension();
    
   /**
    * This will return the full name of the file without the path.
    * As regargs the definition of the path in RFC 2396 the name
    * would be considered the last path segment. So if the path 
    * was <code>/usr/README</code> the name is <code>README</code>.
    * Also for directorys the name of the directory in the last
    * path segment is returned. This returns the name without any
    * of the path parameters. As RFC 2396 defines the path to have
    * path parameters after the path segments.
    *
    * @return this will return the name of the file in the path
    */    
   public String getName();

//   public String getPathSeg();

   public String[] getSegments();

}    
