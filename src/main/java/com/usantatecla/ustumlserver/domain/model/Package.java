package com.usantatecla.ustumlserver.domain.model;

import java.util.List;

class Package extends Member {

    private List<Member> members;

    Package(String name, List<Member> members) {
        super(name);
        this.members = members;
    }

    @Override
    void accept(Generator generator) {
        generator.visit(this);
    }

}
