package Utilities.seleniumcustomframework.extension.dependencies;

import Utilities.seleniumcustomframework.extension.PageFactory;

public class InternalGuiceDependencyInjection extends GuiceDependencyInjector {
    private PageFactory pageFactory;
    private final DependencyInjector dependencyInjector;

    public InternalGuiceDependencyInjection(PageFactory pageFactory,DependencyInjector dependencyInjector,DependencyFactory... factories) {
        super(pageFactory,factories);
        this.modules.add(new FieldInitialiserDependencyModule());
        this.pageFactory=pageFactory;
        this.dependencyInjector=dependencyInjector;
    }
    protected void configure(){
        super.configure();
        this.bind(DependencyInjector.class).toInstance(this.dependencyInjector);
    }
}
