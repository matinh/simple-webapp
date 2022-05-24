# Simple WebApp to show IllegalAccessException with JDK 17

This is a minimalistic web application to illustrate a problem with Java 17 [I asked for help](https://stackoverflow.com/questions/72361100/) on stackoverflow.
It uses the following application stack:

* JDK 17
* JakartaEE8
* JSF 2.3
* Weld 3.1

When deployed to Wildfly 26.1.1.Final, an access to index.xhtml leads to the
following stacktrace:

```text
10:14:17,651 SEVERE [javax.enterprise.resource.webcontainer.jsf.application] (default task-1) Error Rendering View[/index.xhtml]: javax.el.ELException: /index.xhtml @23,74 value="raw offset=#{myWarBean.defaultTZ.rawOffset}": java.lang.IllegalAccessException: class javax.el.BeanELResolver cannot access class sun.util.calendar.ZoneInfo (in module java.base) because module java.base does not export sun.util.calendar to unnamed module @72b47abc
	at com.sun.jsf-impl@2.3.17.SP01//com.sun.faces.facelets.el.TagValueExpression.getValue(TagValueExpression.java:77)
	at javax.faces.api@3.1.0.SP01//javax.faces.component.ComponentStateHelper.eval(ComponentStateHelper.java:194)
	at javax.faces.api@3.1.0.SP01//javax.faces.component.ComponentStateHelper.eval(ComponentStateHelper.java:181)
	at javax.faces.api@3.1.0.SP01//javax.faces.component.UIOutput.getValue(UIOutput.java:140)
	at com.sun.jsf-impl@2.3.17.SP01//com.sun.faces.renderkit.html_basic.HtmlBasicInputRenderer.getValue(HtmlBasicInputRenderer.java:198)
	at com.sun.jsf-impl@2.3.17.SP01//com.sun.faces.renderkit.html_basic.HtmlBasicRenderer.getCurrentValue(HtmlBasicRenderer.java:328)
	at com.sun.jsf-impl@2.3.17.SP01//com.sun.faces.renderkit.html_basic.HtmlBasicRenderer.encodeEnd(HtmlBasicRenderer.java:143)
	at javax.faces.api@3.1.0.SP01//javax.faces.component.UIComponentBase.encodeEnd(UIComponentBase.java:600)
	at com.sun.jsf-impl@2.3.17.SP01//com.sun.faces.renderkit.html_basic.HtmlBasicRenderer.encodeRecursive(HtmlBasicRenderer.java:286)
	at com.sun.jsf-impl@2.3.17.SP01//com.sun.faces.renderkit.html_basic.GroupRenderer.encodeChildren(GroupRenderer.java:90)
	at javax.faces.api@3.1.0.SP01//javax.faces.component.UIComponentBase.encodeChildren(UIComponentBase.java:571)
	at javax.faces.api@3.1.0.SP01//javax.faces.component.UIComponent.encodeAll(UIComponent.java:1648)
	at javax.faces.api@3.1.0.SP01//javax.faces.component.UIComponent.encodeAll(UIComponent.java:1651)
	at javax.faces.api@3.1.0.SP01//javax.faces.component.UIComponent.encodeAll(UIComponent.java:1651)
	at com.sun.jsf-impl@2.3.17.SP01//com.sun.faces.application.view.FaceletViewHandlingStrategy.renderView(FaceletViewHandlingStrategy.java:461)
	at com.sun.jsf-impl@2.3.17.SP01//com.sun.faces.application.view.MultiViewHandler.renderView(MultiViewHandler.java:170)
	at javax.faces.api@3.1.0.SP01//javax.faces.application.ViewHandlerWrapper.renderView(ViewHandlerWrapper.java:132)
	at javax.faces.api@3.1.0.SP01//javax.faces.application.ViewHandlerWrapper.renderView(ViewHandlerWrapper.java:132)
	at javax.faces.api@3.1.0.SP01//javax.faces.application.ViewHandlerWrapper.renderView(ViewHandlerWrapper.java:132)
	at deployment.my-war-1.0-SNAPSHOT.war//org.omnifaces.viewhandler.OmniViewHandler.renderView(OmniViewHandler.java:151)
	at com.sun.jsf-impl@2.3.17.SP01//com.sun.faces.lifecycle.RenderResponsePhase.execute(RenderResponsePhase.java:102)
	at com.sun.jsf-impl@2.3.17.SP01//com.sun.faces.lifecycle.Phase.doPhase(Phase.java:76)
	at com.sun.jsf-impl@2.3.17.SP01//com.sun.faces.lifecycle.LifecycleImpl.render(LifecycleImpl.java:199)
	at javax.faces.api@3.1.0.SP01//javax.faces.webapp.FacesServlet.executeLifecyle(FacesServlet.java:708)
	at javax.faces.api@3.1.0.SP01//javax.faces.webapp.FacesServlet.service(FacesServlet.java:451)
	at io.undertow.servlet@2.2.17.Final//io.undertow.servlet.handlers.ServletHandler.handleRequest(ServletHandler.java:74)
	at io.undertow.servlet@2.2.17.Final//io.undertow.servlet.handlers.FilterHandler$FilterChainImpl.doFilter(FilterHandler.java:129)
	at io.opentracing.contrib.opentracing-jaxrs2//io.opentracing.contrib.jaxrs2.server.SpanFinishingFilter.doFilter(SpanFinishingFilter.java:52)
	at io.undertow.servlet@2.2.17.Final//io.undertow.servlet.core.ManagedFilter.doFilter(ManagedFilter.java:61)
	at io.undertow.servlet@2.2.17.Final//io.undertow.servlet.handlers.FilterHandler$FilterChainImpl.doFilter(FilterHandler.java:131)
	at io.undertow.servlet@2.2.17.Final//io.undertow.servlet.handlers.FilterHandler.handleRequest(FilterHandler.java:84)
	at io.undertow.servlet@2.2.17.Final//io.undertow.servlet.handlers.ServletChain$1.handleRequest(ServletChain.java:68)
	at io.undertow.servlet@2.2.17.Final//io.undertow.servlet.handlers.ServletDispatchingHandler.handleRequest(ServletDispatchingHandler.java:36)
	at io.undertow.servlet@2.2.17.Final//io.undertow.servlet.handlers.RedirectDirHandler.handleRequest(RedirectDirHandler.java:68)
	at org.wildfly.extension.undertow@26.1.1.Final//org.wildfly.extension.undertow.deployment.GlobalRequestControllerHandler.handleRequest(GlobalRequestControllerHandler.java:68)
	at io.undertow.servlet@2.2.17.Final//io.undertow.servlet.handlers.SendErrorPageHandler.handleRequest(SendErrorPageHandler.java:52)
	at io.undertow.core@2.2.17.Final//io.undertow.server.handlers.PredicateHandler.handleRequest(PredicateHandler.java:43)
	at io.undertow.servlet@2.2.17.Final//io.undertow.servlet.handlers.ServletInitialHandler.handleFirstRequest(ServletInitialHandler.java:275)
	at io.undertow.servlet@2.2.17.Final//io.undertow.servlet.handlers.ServletInitialHandler.access$100(ServletInitialHandler.java:79)
	at io.undertow.servlet@2.2.17.Final//io.undertow.servlet.handlers.ServletInitialHandler$2.call(ServletInitialHandler.java:134)
	at io.undertow.servlet@2.2.17.Final//io.undertow.servlet.handlers.ServletInitialHandler$2.call(ServletInitialHandler.java:131)
	at io.undertow.servlet@2.2.17.Final//io.undertow.servlet.core.ServletRequestContextThreadSetupAction$1.call(ServletRequestContextThreadSetupAction.java:48)
	at io.undertow.servlet@2.2.17.Final//io.undertow.servlet.core.ContextClassLoaderSetupAction$1.call(ContextClassLoaderSetupAction.java:43)
	at org.wildfly.extension.undertow@26.1.1.Final//org.wildfly.extension.undertow.deployment.UndertowDeploymentInfoService$UndertowThreadSetupAction.lambda$create$0(UndertowDeploymentInfoService.java:1544)
	at org.wildfly.extension.undertow@26.1.1.Final//org.wildfly.extension.undertow.deployment.UndertowDeploymentInfoService$UndertowThreadSetupAction.lambda$create$0(UndertowDeploymentInfoService.java:1544)
	at org.wildfly.extension.undertow@26.1.1.Final//org.wildfly.extension.undertow.deployment.UndertowDeploymentInfoService$UndertowThreadSetupAction.lambda$create$0(UndertowDeploymentInfoService.java:1544)
	at org.wildfly.extension.undertow@26.1.1.Final//org.wildfly.extension.undertow.deployment.UndertowDeploymentInfoService$UndertowThreadSetupAction.lambda$create$0(UndertowDeploymentInfoService.java:1544)
	at io.undertow.servlet@2.2.17.Final//io.undertow.servlet.handlers.ServletInitialHandler.dispatchRequest(ServletInitialHandler.java:255)
	at io.undertow.servlet@2.2.17.Final//io.undertow.servlet.handlers.ServletInitialHandler.access$000(ServletInitialHandler.java:79)
	at io.undertow.servlet@2.2.17.Final//io.undertow.servlet.handlers.ServletInitialHandler$1.handleRequest(ServletInitialHandler.java:100)
	at io.undertow.core@2.2.17.Final//io.undertow.server.Connectors.executeRootHandler(Connectors.java:387)
	at io.undertow.core@2.2.17.Final//io.undertow.server.HttpServerExchange$1.run(HttpServerExchange.java:852)
	at org.jboss.threads@2.4.0.Final//org.jboss.threads.ContextClassLoaderSavingRunnable.run(ContextClassLoaderSavingRunnable.java:35)
	at org.jboss.threads@2.4.0.Final//org.jboss.threads.EnhancedQueueExecutor.safeRun(EnhancedQueueExecutor.java:1990)
	at org.jboss.threads@2.4.0.Final//org.jboss.threads.EnhancedQueueExecutor$ThreadBody.doRunTask(EnhancedQueueExecutor.java:1486)
	at org.jboss.threads@2.4.0.Final//org.jboss.threads.EnhancedQueueExecutor$ThreadBody.run(EnhancedQueueExecutor.java:1377)
	at org.jboss.xnio@3.8.7.Final//org.xnio.XnioWorker$WorkerThreadFactory$1$1.run(XnioWorker.java:1282)
	at java.base/java.lang.Thread.run(Thread.java:833)
Caused by: javax.el.ELException: java.lang.IllegalAccessException: class javax.el.BeanELResolver cannot access class sun.util.calendar.ZoneInfo (in module java.base) because module java.base does not export sun.util.calendar to unnamed module @72b47abc
	at javax.el.api@2.0.0.Final//javax.el.BeanELResolver.getValue(BeanELResolver.java:193)
	at com.sun.jsf-impl@2.3.17.SP01//com.sun.faces.el.DemuxCompositeELResolver._getValue(DemuxCompositeELResolver.java:156)
	at com.sun.jsf-impl@2.3.17.SP01//com.sun.faces.el.DemuxCompositeELResolver.getValue(DemuxCompositeELResolver.java:184)
	at org.glassfish.jakarta.el@3.0.3.jbossorg-4//com.sun.el.parser.AstValue.getValue(AstValue.java:114)
	at org.glassfish.jakarta.el@3.0.3.jbossorg-4//com.sun.el.parser.AstValue.getValue(AstValue.java:177)
	at org.glassfish.jakarta.el@3.0.3.jbossorg-4//com.sun.el.parser.AstDeferredExpression.getValue(AstDeferredExpression.java:39)
	at org.glassfish.jakarta.el@3.0.3.jbossorg-4//com.sun.el.parser.AstCompositeExpression.getValue(AstCompositeExpression.java:44)
	at org.glassfish.jakarta.el@3.0.3.jbossorg-4//com.sun.el.ValueExpressionImpl.getValue(ValueExpressionImpl.java:183)
	at org.jboss.weld.core@3.1.9.Final//org.jboss.weld.module.web.el.WeldValueExpression.getValue(WeldValueExpression.java:50)
	at org.jboss.weld.core@3.1.9.Final//org.jboss.weld.module.web.el.WeldValueExpression.getValue(WeldValueExpression.java:50)
	at com.sun.jsf-impl@2.3.17.SP01//com.sun.faces.facelets.el.TagValueExpression.getValue(TagValueExpression.java:73)
	... 57 more
Caused by: java.lang.IllegalAccessException: class javax.el.BeanELResolver cannot access class sun.util.calendar.ZoneInfo (in module java.base) because module java.base does not export sun.util.calendar to unnamed module @72b47abc
	at java.base/jdk.internal.reflect.Reflection.newIllegalAccessException(Reflection.java:392)
	at java.base/java.lang.reflect.AccessibleObject.checkAccess(AccessibleObject.java:674)
	at java.base/java.lang.reflect.Method.invoke(Method.java:560)
	at javax.el.api@2.0.0.Final//javax.el.BeanELResolver.getValue(BeanELResolver.java:186)
	... 67 more
```

## Build and Deploy

To build the application simply run

```shell
mvn package
```

You will then find a WAR in the `target` directory which you can copy to
wildfly's deploy directory:

```shell
cp target/my-war-1.0-SNAPSHOT.war ${WILDFLY_HOME}/standalone/deployments/
```

## Access the Application

Once deployed to Wildfly, you can access the application by pointing your
browser to

[http://localhost:8080//my-war-1.0-SNAPSHOT/](http://localhost:8080//my-war-1.0-SNAPSHOT/)


## Solution

This is an [issue in the EL specification](https://github.com/jakartaee/expression-language/issues/188),
which was reported by BalusC right after [my question](https://stackoverflow.com/questions/72361100) was published. 

A workaround suggested as an 
[answer on StackOverflow](https://stackoverflow.com/questions/72361100/illegalaccessexception-when-accessing-zoneinfo-via-jsf-el-with-jdk-17#72362656)
is to provide a Getter for the `rawOffset` attribute

```java
public int getDefaultTZrawOffset() {
  return getDefaultTZ().getRawOffset();
}
```

and use this in the EL:

```
#{myWarBean.defaultTZrawOffset}
```
