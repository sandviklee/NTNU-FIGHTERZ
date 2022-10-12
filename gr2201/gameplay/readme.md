# Gameplay beskrivelse

Gameplay er alle klassene som har noe med logikken til spillet å gjøre. 
Her skal klassene **Action**, **AnimationSprite**, **Effectbox**, **Player**, **Point**, **Terrain**, **Projectile**, **Vector**, **World** og **WorldEntity** være.

---

**World** klassen holder på alle "enteties" som er i verden og logikken for å få alle entities til å kunne handle. Verdenen skal også gi tilgang til tilstanden så controlleren kan sende info til UI om hvordan verden ser ut ved det tidspunktet.

**WorldEntity** klassen er en superklasse for alle typer "entities" som skal dannes i verdenen. Den skal også holde på alle felles metoder og felter for alle entities i verden.

**Projectile** klassen lager projectiles, arver WorldEntity.

**Terrain** klassen lager terrain, arver WorldEntity.

**Character** klassen er karakteren selv, arver WorldEntity.

**Action** klassen er for å lage "actions" til karakterene i spillet. 

**AnimationSprite** klassen skal inneholde logikken til å spille av animasjonene til Players/Projectiles sine actions. Denne klassen kan så bli hentet av actions slik at animasjonen kan spilles av.

**Effectbox** klassen er Hitbox/Hurtbox logikken, slik at Players/projectiles kan påvirke hverandre.

**Vector** klassen lager vektorer, dette for bevegelse.

**Point** klassen styrer posisjonen til worldEntities (Players, Projectiles). Dette er et punkt midt i karakteren og den som skal bli påvirket av vektorer.


---