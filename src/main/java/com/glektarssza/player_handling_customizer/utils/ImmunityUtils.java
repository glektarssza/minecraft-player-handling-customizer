package com.glektarssza.player_handling_customizer.utils;

import java.util.List;
import java.util.regex.Pattern;

import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;

import com.glektarssza.player_handling_customizer.api.ITargetingImmunity;

/**
 * A collection of utilities for working with immunities.
 */
public class ImmunityUtils {
    /**
     * Check if an entity matches against an immunity from being targeted.
     *
     * @param entity The entity to compared against the targeting immunity.
     * @param immunity The immunity to check against.
     *
     * @return {@code true} if the immunity makes the subject immune to being
     *         targeted by the entity; {@code false} otherwise.
     */
    public static boolean entityMatchesTargetingImmunity(
        EntityLivingBase entity, ITargetingImmunity immunity) {
        String immunityId = immunity.getEntityType();
        String entityRL = EntityList.getKey(entity).toString().toLowerCase();
        if (immunityId.equals("")) {
            return false;
        }
        if (immunityId.equals("*")) {
            return true;
        }
        if (immunityId.contains("*")) {
            return Pattern.matches(immunityId.replace("*", "[a-zA-Z0-9_-/]+"),
                entityRL);
        }
        return entityRL.equals(immunityId);
    }

    /**
     * Check if an entity matches against any of a list of immunities from being
     * targeted.
     *
     * @param entity The entity to compared against the targeting immunities.
     * @param immunityList The list of immunities to check against.
     *
     * @return {@code true} if at least one of the immunities makes the subject
     *         immune to being targeted by the entity; {@code false} otherwise.
     */
    public static boolean entityMatchesAnyTargetingImmunity(
        EntityLivingBase entity, List<ITargetingImmunity> immunityList) {
        return immunityList.stream().anyMatch((immunity) -> ImmunityUtils
            .entityMatchesTargetingImmunity(entity, immunity));
    }
}
