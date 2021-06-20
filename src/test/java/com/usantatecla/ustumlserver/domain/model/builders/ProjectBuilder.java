package com.usantatecla.ustumlserver.domain.model.builders;

import com.usantatecla.ustumlserver.domain.model.Class;
import com.usantatecla.ustumlserver.domain.model.Member;
import com.usantatecla.ustumlserver.domain.model.Package;
import com.usantatecla.ustumlserver.domain.model.Project;

public class ProjectBuilder extends PackageBuilder {

    public ProjectBuilder() {
        super();
    }

    public ProjectBuilder(Project project) {
        super(project);
    }

    @Override
    public ProjectBuilder id(String id) {
        super.id(id);
        return this;
    }

    @Override
    public ProjectBuilder name(String name) {
        super.name(name);
        return this;
    }

    @Override
    public ProjectBuilder packages(Package... packages) {
        super.packages(packages);
        return this;
    }

    @Override
    public ProjectBuilder pakage(Package pakage) {
        super.pakage(pakage);
        return this;
    }

    @Override
    public ProjectBuilder pakage() {
        super.pakage();
        return this;
    }

    @Override
    public ProjectBuilder clazz() {
        super.clazz();
        return this;
    }

    @Override
    public ProjectBuilder classes(Class... classes) {
        super.classes(classes);
        return this;
    }

    @Override
    public ProjectBuilder use() {
        super.use();
        return this;
    }

    @Override
    public ProjectBuilder target(Member member) {
        super.target(member);
        return this;
    }

    @Override
    public ProjectBuilder role(String role) {
        super.role(role);
        return this;
    }

    @Override
    public Project build() {
        super.build();
        Project project = new Project(this.name, this.members);
        project.setId(this.id);
        this.setRelations(project);
        return project;
    }
}
