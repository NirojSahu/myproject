//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package Utilities.seleniumcustomframework.extension.dependencies;

import Utilities.seleniumcustomframework.extension.PageFactory;

class InternalGuiceDependencyInjector extends GuiceDependencyInjector {
    private PageFactory pageFactory;
    private final DependencyInjector dependencyInjector;

    public InternalGuiceDependencyInjector(PageFactory pageFactory, DependencyInjector dependencyInjector, DependencyFactory... factories) {
        super(pageFactory, factories);
        this.modules.add(new FieldInitialiserDependencyModule());
        this.pageFactory = pageFactory;
        this.dependencyInjector = dependencyInjector;
    }

    protected void configure() {
        super.configure();
        this.bind(DependencyInjector.class).toInstance(this.dependencyInjector);
    }
}
