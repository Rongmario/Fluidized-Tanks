package zone.rong.fluidizedtanks.client;

import net.minecraft.client.color.block.BlockColor;
import net.minecraft.client.color.item.ItemColor;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.Nullable;
import zone.rong.fluidizedtanks.block.TankBlockEntity;
import zone.rong.fluidizedtanks.data.TankDefinition;

public class TankBlockColor implements BlockColor, ItemColor {
    @Override
    @OnlyIn(Dist.CLIENT)
    public int getColor(BlockState state, @Nullable BlockAndTintGetter level, @Nullable BlockPos pos, int tintIndex) {
        if (tintIndex == 0) {
            if (level != null && pos != null) {
                if (level.getBlockEntity(pos) instanceof TankBlockEntity tank) {
                    return tank.getTankDefinition().map(TankDefinition::colour).orElse(-1);
                }
            }
        }
        return -1;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public int getColor(ItemStack stack, int tintIndex) {
        if (tintIndex == 0) {
            TankDefinition tankDefinition = TankDefinition.get(stack);
            if (tankDefinition != null) {
                return tankDefinition.colour();
            }
        }
        return -1;
    }
}
