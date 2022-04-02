package VeryGoodViroGame;

/**
 * Ez egy interface, ami előírja, hogy milyen metódusokkal kell rendelkeznie azoknak az osztályoknak,
 * melyek entitásai az idő hatására változnak.
 */
public interface iMoveStrategy
{
    /**
     * Azt a metódust kell felüldefiniálnia az időben változó entitásoknak.
     * @param v A virológus, akin a mozgást végrehajtjuk.
     * @param from a Mező amiről mozgatjuk a virológust
     * @param to a Mező amire mozgatjuk a virológust
     */
    void ExecuteMove(Virologist v, Field from, Field to);
}
