package com.usantatecla.ustumlserver.domain.model;

import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
public class Package extends Member {

    private List<Member> members;

    public Package(String name, List<Member> members) {
        super(name);
        this.members = members;
    }

    public void add(Member member) {
        this.members.add(member);
    }

    @Override
    public void accept(Generator generator) {
        generator.visit(this);
    }

}
