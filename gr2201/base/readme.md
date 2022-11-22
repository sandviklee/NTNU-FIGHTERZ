# Base beskrivelse

Base inneholder pakkene: **dbaccess**, **users** og **json**. Base omhandler alt om brukere og innloggings funksjonalitet.
Dette er hvor alt med brukerprofiler blir gjort.

---

**dbaccess** er klasser som lagrer brukerinformasjon om brukere til fil, men også lese info fra fil og lage brukere.

**users** er klasser som har alt med dannelse av brukere og annen funksjonalitet. 

**json** er også en module, og inneholder deserializers og serializers for brukere. 



# `Persistence`
This module is meant to represent the persistence layer and uses DAOs as its only connection point to the persistence layer. It contains all the logic for saving and loading data for the program.

## The `DAO` package:
The project makes use of the DAO (Data Access Object) design pattern to access information stored in data storage. The DAO package contains all DAOs in the project.

### `CRUDDAO`
The CRUDDAO (Create, Read, Update, Delete Data Access Object) is a generic interface supporting:
- add(T object) - create
- ArrayList<T> loadAll() - reads all regardless of attributes
- T get(K id) - reads all regardless of attributes
- update(K id, T object) - update
- delete(K id) - delete

The module also has an implementation of this interface `UserDAOImpl` for user information.

### `RODAO`
The RODAO (Read Only Data Access Object) is a generic interface supporting:
- ArrayList<T> getAll() - reads all regardless of attributes
- T find(K id) - reads all regardless of attributes 

Two classes implement this interface `CharacterInformationDAOImpl` and `CharacterAttriburesDAOImpl`.

## The `utils` package:

`ReadWriteToFile` gives the DAO implementations a way to read the content of files and write to them.

### `json` sub package
Contains all the serializers, deserializers and modules for objectmappers for Users, CharacterAttributes and CharacterInfo.


