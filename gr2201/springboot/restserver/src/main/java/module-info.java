module fightinggame.springboot.restserver {
    requires spring.web;
    requires spring.beans;
    requires spring.boot;
    requires spring.context;
    requires spring.boot.autoconfigure;

    requires fightinggame.base;

    opens fightinggame.springboot.restserver to spring.beans, spring.context, spring.web;
}
