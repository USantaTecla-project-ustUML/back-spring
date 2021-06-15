package com.usantatecla.ustumlserver.domain.services.interpreters;

import com.usantatecla.ustumlserver.domain.model.*;
import com.usantatecla.ustumlserver.domain.model.Package;
import com.usantatecla.ustumlserver.domain.services.ServiceException;
import com.usantatecla.ustumlserver.domain.services.parsers.MemberType;
import com.usantatecla.ustumlserver.domain.services.parsers.ParserException;
import com.usantatecla.ustumlserver.domain.services.parsers.ProjectParser;
import com.usantatecla.ustumlserver.domain.services.parsers.RelationParser;
import com.usantatecla.ustumlserver.infrastructure.api.dtos.Command;
import com.usantatecla.ustumlserver.infrastructure.api.dtos.ErrorMessage;

import java.util.ArrayList;
import java.util.List;

public abstract class MemberInterpreter {

    protected Account account;
    protected Member member;

    protected MemberInterpreter(Account account, Member member) {
        this.account = account;
        this.member = member;
    }

    public void add(Command command) {
        if (!command.has(Command.MEMBERS) && !command.has(Command.RELATIONS)) {
            throw new ParserException(ErrorMessage.KEY_NOT_FOUND, "Members or Relations");
        }
    }

    public void delete(Command command) {
        if (!command.has(Command.MEMBERS) && !command.has(Command.RELATIONS)) {
            throw new ParserException(ErrorMessage.KEY_NOT_FOUND, "Members or Relations");
        }
    }

    public void _import(Command command) {
        throw new ServiceException(ErrorMessage.IMPORT_NOT_ALLOWED);
    }

    public Member open(Command command) {
        throw new ServiceException(ErrorMessage.OPEN_NOT_ALLOWED);
    }

    protected void addRelations(Command command) {
        for (Command relationCommand : command.getCommands(Command.RELATIONS)) {
            this.member.addRelation(new RelationParser().get(this.account, relationCommand));
        }
    }

    protected List<Relation> deleteRelations(Command command) {
        List<Relation> relations = new ArrayList<>();
        for (Command relationCommand : command.getCommands(Command.RELATIONS)) {
            relations.add(new RelationParser().get(this.account, relationCommand));
        }
        return relations;
    }

    public Member getMember() {
        return this.member;
    }
}
