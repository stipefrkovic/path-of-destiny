package nl.rug.oop.npc;

import nl.rug.oop.effects.Effect;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Abstract class that is the super class of the player and NPCs, which allows for both of them being affected by Effects.
 * @author Jonas Scholz
 */
public abstract class Entity implements Serializable {

    protected String name;
    protected int health;
    protected int maxHealth;
    protected float strength;
    protected int gold;

    private float baseStrength;
    private double confusionChance;
    private double stunnedChance;
    private ArrayList<Effect> effects;
    private ArrayList<Effect> removeEffects;

    /**
     * Initializes the attributes of the entity.
     * @param name The name of the entity.
     * @param maxHealth The maximal health of the entity.
     * @param strength The strength of the entity.
     */
    protected Entity(String name, int maxHealth, float strength){
        this.name = name;
        this.maxHealth = maxHealth;
        this.health = maxHealth;
        this.strength = strength;
        this.baseStrength = strength;
        this.confusionChance = 0;
        this.stunnedChance = 0;
        this.effects = new ArrayList<>();
        this.removeEffects = new ArrayList<>();
    }

    /**
     * Updates the effects and removes the effects on the removal list.
     */
    protected void updateEffects(){
        effects.removeAll(removeEffects);
        removeEffects.clear();
        this.strength = this.baseStrength;
        for (Effect effect:effects) {
            effect.update(this);
        }
    }

    /**
     * Adds an effect to the entity.
     * @param effect The effect that is supposed to be added to the entity.
     * @return A description of what happened.
     */
    public String addEffect(Effect effect){
        effects.add(effect);
        return getName() + " is " + effect.getEffectAdjective()+ ".";
    }

    /**
     * Removes the effect in a safe manner, by adding it to a removal list.
     * @param effect The effect that is supposed to be deleted.
     */
    public void removeEffect(Effect effect){
        removeEffects.add(effect);
    }

    /**
     * Removes all effects, by adding them to the removal queue.
     */
    public void removeAllEffects(){
        removeEffects.addAll(effects);
    }

    /**
     * Returns the maximal health of the entity.
     * @return The maximal health of the entity.
     */
    public int getMaxHealth() {
        return maxHealth;
    }

    /**
     * Changes the maximum health by the specified amount and checks that health stays below the maximum health.
     * @param amount The amount by which the maximum health changes.
     * @return The description of what changed.
     */
    public String changeMaxHealth(int amount) {
        String description = this.getName();
        if(amount>0){
            this.health += amount;
            description += " has gained " + amount + " to their maximum health.";
        }else{
            description += " has lost " + amount + "of their maximum health.";
        }
        this.maxHealth = Math.max(1, this.maxHealth+amount);
        this.health = Math.min(this.maxHealth, this.health);
        return description;
    }

    /**
     * Returns the name of the entity.
     * @return The name of the entity.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the current health of the entity.
     * @return The current health of the entity.
     */
    public int getHealth() {
        return health;
    }

    /**
     * Returns the current strength, this value is influenced by temporary multipliers.
     * @return The current strength.
     */
    public float getStrength() {
        return strength;
    }

    /**
     * Returns the base (permanent) strength of the entity.
     * @return The base (permanent) strength of the entity.
     */
    public float getBaseStrength() {
        return baseStrength;
    }

    /**
     * Sets the base strength (permanent strength)
     * @param baseStrength The new base strength
     */
    public void setBaseStrength(float baseStrength) {
        this.baseStrength = baseStrength;
    }

    /**
     * Returns the gold that the entity has.
     * @return The gold that the entity has.
     */
    public int getGold() {
        return gold;
    }

    /**
     * Reduces the health by the specified amount and gives a string of the action back. For non-Entity damage please call the changeHealth function.
     * @param attacker The entity that attcked this entity.
     * @param damage The amount of damage that was inflicted.
     * @return The description of what happened.
     */
    public String takeDamage(Entity attacker, int damage){
        health = Math.max(0, health - damage);
        return attacker.getName()+" has attacked "+ this.getName() + " for " +  damage + " damage.";
    }

    /**
     * Multiplies the strength by the amount specified, this is a temporary change, as the base strength is unaffected, so only until the updateEffect function is called again.
     * @param multiplier The multiplier by which the strength should be multiplied.
     */
    public void changeStrengthTemporary(float multiplier){
        this.strength = Math.max(0, this.strength*multiplier);
    }

    /**
     * Changes the health of the player, while keeping it in appropriate limits.
     * @param healthChange The amount by which the health changes.
     */
    public void changeHealth(int healthChange){
        this.health = Math.min(Math.max(0, this.health - healthChange), this.maxHealth);
    }

    /**
     * Can be used to change the amount of gold an Entity has.
     * @param amount The amount by which the amount of gold is changed.
     * @return If the transaction was successful or if the change would have left the entity in the minus.
     */
    public boolean changeGoldAmount(int amount){
        if(gold+amount>=0){
            gold += amount;
            return true;
        }
        return false;
    }

    /**
     * Sets the confusion chance, to a value between 0 and 1.
     * @param confusionChance A value between
     */
    public void setConfusionChance(double confusionChance) {
        this.confusionChance = Math.min(Math.max(confusionChance, 0), 1);
    }

    /**
     * Sets the stun chance, to a value between 0 and 1.
     * @param stunnedChance
     */
    public void setStunnedChance(double stunnedChance) {
        this.stunnedChance = Math.min(Math.max(stunnedChance, 0), 1);
    }

    /**
     * Determines if the entity is confused and therefore attacks itself or an ally and sets the confusionChance to 0..
     * @param damage The amount of damage the entity will deal to itself or an ally, in case it is confused.
     * @param allies A List of allies, which might be targeted, if the entity is confused.
     * @return Returns null if the entity is not confused and otherwise an appropriate description of what happened.
     */
    protected String isConfused(int damage, List<NPC> allies){
        String description = null;
        if(Math.random()<confusionChance){
            int randIndx = (int) (Math.random()*(allies.size()+1));
            if(randIndx == allies.size()) {
                description = this.takeDamage(this, damage);
            }else{
                description = allies.get(randIndx).takeDamage(this, damage);
            }
        }
        confusionChance = 0;
        return description;
    }

    /**
     * Determines if the entity is confused and therefore attacks itself and sets the confusionChance to 0.
     * @param damage The amount of damage the entity will deal to itself, in case it is confused.
     * @return Returns null if the entity is not confused and otherwise an appropriate description of what happened.
     */
    protected String isConfused(int damage){
        return isConfused(damage, new ArrayList<>());
    }

    /**
     * Determines if the entity is stunned and sets the stunnedChance to 0.
     * @return If the entity is stunned.
     */
    protected boolean isStunned(){
        boolean isStunned = Math.random()<stunnedChance;
        stunnedChance = 0;
        return isStunned;
    }
}
