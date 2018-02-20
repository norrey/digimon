package com.norrey.puzzle.model;

import java.io.Serializable;

/**
 *
 * @author Norrey Okumu <okumu.norrey@gmail.com>
 * @since Feb 17, 2018, 8:29:56 AM
 */
public abstract class Character implements Serializable {

    private static final long serialVersionUID = 6250618296067871147L;

    private String name;
    private String description;
    private String introduction;
    private boolean alive;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    @Override
    public String toString() {
        return "Character{" + "name=" + name + ", "
               + "description=" + description + ", "
               + "introduction=" + introduction + ", "
               + "alive=" + alive + '}';
    }

}
