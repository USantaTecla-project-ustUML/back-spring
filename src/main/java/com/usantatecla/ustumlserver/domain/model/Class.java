package com.usantatecla.ustumlserver.domain.model;

import com.usantatecla.ustumlserver.domain.persistence.MemberVisitor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Class extends Member {

    private List<Modifier> modifiers;
    private List<Attribute> attributes;
    private List<Method> methods;

    public Class(String name, List<Modifier> modifiers, List<Attribute> attributes) {
        super(name);
        if (!Modifier.isThereVisibility(modifiers)) {
            modifiers.add(0, Modifier.PACKAGE);
        }
        this.modifiers = modifiers;
        this.attributes = attributes;
        this.methods = new ArrayList<>();
    }

    public static boolean matchesModifiers(String modifiers) {
        return modifiers.matches("((" + Modifier.PUBLIC.getUstUML() + "( +" + Modifier.ABSTRACT.getUstUML()
                + ")?)|(" + Modifier.PACKAGE.getUstUML() + "( +" + Modifier.ABSTRACT.getUstUML() + ")?)|"
                + Modifier.ABSTRACT.getUstUML() + ")");
    }

    @Override
    public String accept(Generator generator) {
        return generator.visit(this);
    }

    @Override
    public void accept(MemberVisitor memberVisitor){
        memberVisitor.visit(this);
    }

}
