module fightinggame.springboot.rest {
    requires com.fasterxml.jackson.databind;

    requires spring.web;
    requires spring.beans;
    requires spring.boot;
    requires spring.context;
    requires spring.boot.autoconfigure;

    requires fightinggame.core;

    opens fightinggame.springboot.restserver to spring.beans, spring.context, spring.web;
}
