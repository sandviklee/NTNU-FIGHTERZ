/**
 * The module springboot.restserver is the server api for the project.
 * It can start a server on a port set in application.properties and can recive
 * http requests. The module sets up endpoint and contains logic for handling
 * request on these endpoints.
 */
module fightinggame.springboot.restserver {
    requires spring.web;
    requires spring.beans;
    requires spring.boot;
    requires spring.context;
    requires spring.boot.autoconfigure;

    requires transitive fightinggame.base;

    opens fightinggame.springboot.restserver to spring.beans, spring.context, spring.web;
}
