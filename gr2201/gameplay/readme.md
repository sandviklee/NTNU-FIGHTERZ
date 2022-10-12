# Gameplay beskrivelse

Gameplay er alle klassene som har noe med logikken til spillet å gjøre. 
Her skal klassene **Action**, **AnimationSprite**, **Effectbox**, **Player**, **Point**, **Terrain**, **Projectile**, **Vector**, **World** og **WorldEntity** være.

---

**World** klassen holder på all logikken som skal kommunisere med alt i verdenen og UI.

**WorldEntity** klassen er en superklasse for alle typer "entities" som skal dannes i verdenen.

**Projectile** klassen lager projectiles, arver WorldEntity.

**Terrain** klassen lager terrain, arver WorldEntity.

**Player** klassen er karakteren selv, arver WorldEntity.

**Action** klassen er for å lage "actions" til karakterene i spillet. 

**AnimationSprite** klassen skal spille av animasjonenene til Players/Projectiles sine actions.

**Effectbox** klassen er Hitbox/Hurtbox logikken, slik at Players/projectiles kan påvirke hverandre.

**Vector** klassen lager vektorer, dette for bevegelse.

**Point** klassen styrer posisjonen til worldEntities (Players, Projectiles). Dette er et punkt midt i karakteren og den som skal bli påvirket av vektorer.


---