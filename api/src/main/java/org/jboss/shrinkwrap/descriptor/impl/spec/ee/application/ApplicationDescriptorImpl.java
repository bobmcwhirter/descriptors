/*
 * JBoss, Home of Professional Open Source
 * Copyright 2010, Red Hat Middleware LLC, and individual contributors
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jboss.shrinkwrap.descriptor.impl.spec.ee.application;

import org.jboss.shrinkwrap.descriptor.api.Node;
import org.jboss.shrinkwrap.descriptor.api.spec.ee.application.ApplicationDescriptor;
import org.jboss.shrinkwrap.descriptor.impl.base.NodeProviderImplBase;
import org.jboss.shrinkwrap.descriptor.impl.base.XMLExporter;
import org.jboss.shrinkwrap.descriptor.spi.DescriptorExporter;

/**
 * ApplicationDescriptorImpl
 *
 * @author <a href="mailto:aslak@redhat.com">Aslak Knutsen</a>
 * @version $Revision: $
 */
public class ApplicationDescriptorImpl extends NodeProviderImplBase implements ApplicationDescriptor
{
   //-------------------------------------------------------------------------------------||
   // Instance Members -------------------------------------------------------------------||
   //-------------------------------------------------------------------------------------||

   private Node model;
   
   //-------------------------------------------------------------------------------------||
   // Constructor ------------------------------------------------------------------------||
   //-------------------------------------------------------------------------------------||

   public ApplicationDescriptorImpl()
   {
      this(new Node("application")            
            .attribute("xmlns", "http://java.sun.com/xml/ns/javaee")
            .attribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance"));

   }
   
   public ApplicationDescriptorImpl(Node model)
   {
      this.model = model;
   }
   
   //-------------------------------------------------------------------------------------||
   // API --------------------------------------------------------------------------------||
   //-------------------------------------------------------------------------------------||

   /* (non-Javadoc)
    * @see org.jboss.shrinkwrap.descriptor.api.spec.ee.application.ApplicationDescriptor#description()
    */
   @Override
   public ApplicationDescriptor description(String description)
   {
      model.child("description").text(description);
      return this;
   }
   
   /* (non-Javadoc)
    * @see org.jboss.shrinkwrap.descriptor.api.spec.ee.application.ApplicationDescriptor#displayName(java.lang.String)
    */
   @Override
   public ApplicationDescriptor displayName(String displayName)
   {
      model.child("display-name").text(displayName);
      return this;
   }
   
   /* (non-Javadoc)
    * @see org.jboss.shrinkwrap.descriptor.api.spec.ee.application.ApplicationDescriptor#libraryDirectory(java.lang.String)
    */
   @Override
   public ApplicationDescriptor libraryDirectory(String libraryDirectory)
   {
      model.child("library-directory").text(libraryDirectory);
      return this;
   }
   
   /* (non-Javadoc)
    * @see org.jboss.shrinkwrap.descriptor.api.spec.ee.application.ApplicationDescriptor#ejbModule(java.lang.String)
    */
   public ApplicationDescriptor ejbModule(String uri) 
   {
      model.newChild("module").newChild("ejb").text(uri);
      return this;
   }
   
   /* (non-Javadoc)
    * @see org.jboss.shrinkwrap.descriptor.api.spec.ee.application.ApplicationDescriptor#javaModule(java.lang.String)
    */
   @Override
   public ApplicationDescriptor javaModule(String uri)
   {
      model.newChild("module").newChild("java").text(uri);
      return this;
   }
   
   /* (non-Javadoc)
    * @see org.jboss.shrinkwrap.descriptor.api.spec.ee.application.ApplicationDescriptor#connectorModule(java.lang.String)
    */
   @Override
   public ApplicationDescriptor connectorModule(String uri)
   {
      model.newChild("module").newChild("connector").text(uri);
      return this;
   }
   
   /* (non-Javadoc)
    * @see org.jboss.shrinkwrap.descriptor.api.spec.ee.application.ApplicationDescriptor#webModule(java.lang.String, java.lang.String)
    */
   @Override
   public ApplicationDescriptor webModule(String uri, String contextRoot)
   {
      Node web = model.newChild("module").newChild("web");
      web.newChild("web-uri").text(uri);
      web.newChild("context-root").text(contextRoot);
      return this;
   }
   
   /* (non-Javadoc)
    * @see org.jboss.shrinkwrap.descriptor.api.spec.ee.application.ApplicationDescriptor#version(java.lang.String)
    */
   @Override
   public ApplicationDescriptor version(String version)
   {
      model.attribute("version", version);
      return this;
   }
   
   /* (non-Javadoc)
    * @see org.jboss.shrinkwrap.descriptor.api.spec.ee.application.ApplicationDescriptor#securityRole(java.lang.String)
    */
   @Override
   public ApplicationDescriptor securityRole(String roleName)
   {
      return securityRole(roleName, null);
   }
   
   /* (non-Javadoc)
    * @see org.jboss.shrinkwrap.descriptor.api.spec.ee.application.ApplicationDescriptor#securityRole(java.lang.String, java.lang.String)
    */
   @Override
   public ApplicationDescriptor securityRole(String roleName, String description)
   {
      Node security = model.newChild("security-role");
      if(roleName != null)
      {
         security.newChild("role-name").text(roleName);
      }
      if(description != null)
      {
         security.newChild("description").text(description);
      }
      return this;
   }
   
   //-------------------------------------------------------------------------------------||
   // Required Implementations - NodeProviderImplBase ------------------------------------||
   //-------------------------------------------------------------------------------------||

   /* (non-Javadoc)
    * @see org.jboss.shrinkwrap.descriptor.spi.NodeProvider#getRootNode()
    */
   @Override
   public Node getRootNode()
   {
      return model;
   }
   
   /* (non-Javadoc)
    * @see org.jboss.shrinkwrap.descriptor.impl.base.NodeProviderImplBase#getExporter()
    */
   @Override
   protected DescriptorExporter getExporter()
   {
      return new XMLExporter();
   }
}
