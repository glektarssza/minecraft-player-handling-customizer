package com.glektarssza.playerhandlingcustomizer.api;

import javax.annotation.Nullable;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagString;
import net.minecraftforge.common.util.Constants.NBT;

/**
 * Whether damage is direct or indirect.
 */
public enum DamageDirection {
    /**
     * The damage is coming directly from another entity, for example, via a
     * direct melee attack.
     */
    Direct,

    /**
     * The damage is coming indirectly from another entity, for example, via an
     * arrow.
     */
    Indirect;

    /**
     * Try to get an enumeration value from a NBT string.
     *
     * @param nbt The NBT string to try to get a value from.
     *
     * @return The NBT value upon success; {@code null} otherwise.
     */
    @Nullable
    public static DamageDirection tryFromNBT(NBTBase nbt) {
        byte type = nbt.getId();
        switch (type) {
            case NBT.TAG_STRING:
                return DamageDirection.tryFromNBT((NBTTagString) nbt);
            default:
                return null;
        }
    }

    /**
     * Try to get an enumeration value from a NBT string.
     *
     * @param nbt The NBT string to try to get a value from.
     *
     * @return The NBT value upon success; {@code null} otherwise.
     */
    @Nullable
    public static DamageDirection tryFromNBT(NBTTagString nbt) {
        String str = nbt.getString().toLowerCase();
        if (str == "direct") {
            return Direct;
        }
        if (str == "indirect") {
            return Indirect;
        }
        return null;
    }

    /**
     * Convert a damage direction into a NBT string.
     *
     * @param damageDirection The damage direction to convert.
     *
     * @return The converted damage direction upon success; {@code null}
     *         otherwise.
     */
    @Nullable
    public static NBTTagString toNBT(DamageDirection damageDirection) {
        switch (damageDirection) {
            case Direct:
                return new NBTTagString("direct");
            case Indirect:
                return new NBTTagString("indirect");
            default:
                return null;
        }
    }
}
