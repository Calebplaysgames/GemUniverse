package mod.ke2.tileentity;

import java.util.Iterator;
import java.util.List;

import mod.ke2.KAGIC;
import mod.ke2.api.warping.WarpPadDataEntry;
import mod.ke2.api.warping.pos.GalaxyWarpPadPos;
import mod.ke2.init.Ke2Packets;
import mod.ke2.networking.PacketEntityTeleport;
import mod.ke2.world.data.WorldDataGalaxyWarpPad;
import net.minecraft.block.BlockHalfStoneSlab;
import net.minecraft.block.BlockQuartz;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.BlockSlab.EnumBlockHalf;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.BlockStairs.EnumHalf;
import net.minecraft.block.BlockStairs.EnumShape;
import net.minecraft.block.BlockStoneSlab;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.ForgeChunkManager;
import net.minecraftforge.common.ForgeChunkManager.Ticket;
import net.minecraftforge.common.ForgeChunkManager.Type;

public class TileEntityGalaxyWarpPadCore extends TileEntityWarpPadCore {
	private GalaxyWarpPadPos destination;
	protected GalaxyWarpPadPos getLocation() {
		return new GalaxyWarpPadPos(this.world.provider.getDimension(), this.pos);
	}
	@Override
	protected void setDirty() {
		this.markDirty();
		this.world.notifyBlockUpdate(this.pos, this.world.getBlockState(this.pos), this.world.getBlockState(this.pos), 3);
		if (!this.world.isRemote) {
			WorldDataGalaxyWarpPad.get(this.world).addGalaxyPadEntry(name, this.isPadValid, this.isClear, this.getLocation());
		}
	}
	@Override
	protected boolean validateStairs() {
		for (int x = -1; x <= 1; ++x) {
			IBlockState state = this.world.getBlockState(this.pos.add(x, -1, -3));
			if (state.getBlock() != Blocks.QUARTZ_STAIRS || state.getValue(BlockStairs.FACING) != EnumFacing.SOUTH || state.getValue(BlockStairs.HALF) != EnumHalf.BOTTOM || state.getValue(BlockStairs.SHAPE) != EnumShape.STRAIGHT) {
				return false;
			}
			state = this.world.getBlockState(this.pos.add(x, -1, 3));
			if (state.getBlock() != Blocks.QUARTZ_STAIRS || state.getValue(BlockStairs.FACING) != EnumFacing.NORTH || state.getValue(BlockStairs.HALF) != EnumHalf.BOTTOM || state.getValue(BlockStairs.SHAPE) != EnumShape.STRAIGHT) {
				return false;
			}
		}
		for (int z = -1; z <= 1; ++z) {
			IBlockState state = this.world.getBlockState(this.pos.add(-3, -1, z));
			if (state.getBlock() != Blocks.QUARTZ_STAIRS || state.getValue(BlockStairs.FACING) != EnumFacing.EAST || state.getValue(BlockStairs.HALF) != EnumHalf.BOTTOM || state.getValue(BlockStairs.SHAPE) != EnumShape.STRAIGHT) {
				return false;
			}
			state = this.world.getBlockState(this.pos.add(-3, -1, z));
			if (state.getBlock() != Blocks.QUARTZ_STAIRS || state.getValue(BlockStairs.FACING) != EnumFacing.EAST || state.getValue(BlockStairs.HALF) != EnumHalf.BOTTOM || state.getValue(BlockStairs.SHAPE) != EnumShape.STRAIGHT) {
				return false;
			}
		}
		IBlockState state = this.world.getBlockState(this.pos.add(2, -1, 3));
		if (state.getBlock() != Blocks.STONE_SLAB || state.getValue(BlockHalfStoneSlab.VARIANT) != BlockStoneSlab.EnumType.QUARTZ || state.getValue(BlockSlab.HALF) != EnumBlockHalf.BOTTOM) {
			return false;
		}

		state = this.world.getBlockState(this.pos.add(2, -1, -3));
		if (state.getBlock() != Blocks.STONE_SLAB || state.getValue(BlockHalfStoneSlab.VARIANT) != BlockStoneSlab.EnumType.QUARTZ || state.getValue(BlockSlab.HALF) != EnumBlockHalf.BOTTOM) {
			return false;
		}
		state = this.world.getBlockState(this.pos.add(-2, -1, 3));
		if (state.getBlock() != Blocks.STONE_SLAB || state.getValue(BlockHalfStoneSlab.VARIANT) != BlockStoneSlab.EnumType.QUARTZ || state.getValue(BlockSlab.HALF) != EnumBlockHalf.BOTTOM) {
			return false;
		}
		state = this.world.getBlockState(this.pos.add(-2, -1, -3));
		if (state.getBlock() != Blocks.STONE_SLAB || state.getValue(BlockHalfStoneSlab.VARIANT) != BlockStoneSlab.EnumType.QUARTZ || state.getValue(BlockSlab.HALF) != EnumBlockHalf.BOTTOM) {
			return false;
		}
		state = this.world.getBlockState(this.pos.add(3, -1, 2));
		if (state.getBlock() != Blocks.STONE_SLAB || state.getValue(BlockHalfStoneSlab.VARIANT) != BlockStoneSlab.EnumType.QUARTZ || state.getValue(BlockSlab.HALF) != EnumBlockHalf.BOTTOM) {
			return false;
		}
		state = this.world.getBlockState(this.pos.add(3, -1, -2));
		if (state.getBlock() != Blocks.STONE_SLAB || state.getValue(BlockHalfStoneSlab.VARIANT) != BlockStoneSlab.EnumType.QUARTZ || state.getValue(BlockSlab.HALF) != EnumBlockHalf.BOTTOM) {
			return false;
		}
		state = this.world.getBlockState(this.pos.add(-3, -1, 2));
		if (state.getBlock() != Blocks.STONE_SLAB || state.getValue(BlockHalfStoneSlab.VARIANT) != BlockStoneSlab.EnumType.QUARTZ || state.getValue(BlockSlab.HALF) != EnumBlockHalf.BOTTOM) {
			return false;
		}
		state = this.world.getBlockState(this.pos.add(-3, -1, -2));
		if (state.getBlock() != Blocks.STONE_SLAB || state.getValue(BlockHalfStoneSlab.VARIANT) != BlockStoneSlab.EnumType.QUARTZ || state.getValue(BlockSlab.HALF) != EnumBlockHalf.BOTTOM) {
			return false;
		}
		return super.validateStairs();
	}
	@Override
	protected boolean validatePad() {
		for (int x = -2; x <= 2; ++x) {
			for (int z = -2; z <= 2; ++z) {
				IBlockState state = this.world.getBlockState(this.pos.add(x, -1, z));		
				if (state.getBlock() != Blocks.QUARTZ_BLOCK || state.getValue(BlockQuartz.VARIANT) != BlockQuartz.EnumType.LINES_Y) {
					return false;
				}				
			}
		}
		return super.validatePad();
	}
	public void beginWarp(GalaxyWarpPadPos destination) {
		Ticket ticket = ForgeChunkManager.requestTicket(KAGIC.instance, this.world, Type.NORMAL);
		TileEntityGalaxyWarpPadCore destPad = (TileEntityGalaxyWarpPadCore) this.world.getTileEntity(destination.getPos());
		this.loadPadChunks(this, ticket);
		this.warpTicksLeft = this.warpTicks;
		this.destination = destination;
		this.warping = true;
		this.setDirty();
		this.world.playSound(null, this.pos, ModSounds.WARP_PAD, SoundCategory.BLOCKS, 20.0f, 1.0f);
	}
	@Override
	public void destroy() {
		WorldDataGalaxyWarpPad.get(this.world).removeGalaxyPadEntry(this.getLocation());
	}
	@Override
	public void WARP() {
		int oldDimension = this.world.provider.getDimension();
		int newDimension = this.destination.getDimension();
		if (oldDimension == newDimension) {
			super.destination = this.destination.getPos();
			super.WARP();
			return;
		}
		MinecraftServer server = this.world.getMinecraftServer();
		WorldServer oldWorldServer = server.getWorld(oldDimension);
		WorldServer newWorldServer = server.getWorld(newDimension);

		BlockPos minorCorner = new BlockPos(this.pos.getX() - 1, this.pos.getY() + 1, this.pos.getZ() - 1);
		BlockPos majorCorner = new BlockPos(this.pos.getX() + 2, this.pos.getY() + 5, this.pos.getZ() + 2);
		AxisAlignedBB warpArea = new AxisAlignedBB(minorCorner, majorCorner);
		List<Entity> entitiesToWarp = this.world.getEntitiesWithinAABB(Entity.class, warpArea);
		Iterator<Entity> it = entitiesToWarp.iterator();
		
		WarpPadDataEntry destPad = WorldDataGalaxyPad.get(this.world).getGalaxyPadData().get(this.destination);
		if (!destPad.valid || !destPad.clear) {
			this.cooldownTicksLeft = 1;
			return;
		}
		
		while (it.hasNext()) {
			Entity entity = (Entity) it.next();
			double posX = this.destination.getPos().getX() + entity.posX - this.pos.getX();
			double posY = this.destination.getPos().getY() + entity.posY - this.pos.getY();
			double posZ = this.destination.getPos().getZ() + entity.posZ - this.pos.getZ();

			if (entity instanceof EntityPlayerMP) {
				EntityPlayerMP entityPlayerMP = (EntityPlayerMP) entity;
				server.getPlayerList().transferPlayerToDimension(entityPlayerMP, newDimension, new GalaxyTeleporter(newWorldServer, posX, posY, posZ));
			} else {
				//With thanks to quadraxis from the Minecraft Modding Discord
				entity.dimension = newDimension;
				oldWorldServer.removeEntityDangerously(entity);
				entity.isDead = false;
				server.getPlayerList().transferEntityToWorld(entity, oldDimension, oldWorldServer, newWorldServer, new GalaxyTeleporter(newWorldServer, posX, posY, posZ));
			}
			
			entity.setPositionAndUpdate(posX, posY, posZ);
			if (oldDimension == 1) {
				entity.setPositionAndUpdate(posX, posY, posZ);
				newWorldServer.spawnEntity(entity);
				newWorldServer.updateEntityWithOptionalForce(entity, false);
			}
			
			for (EntityPlayer player : newWorldServer.getEntityTracker().getTrackingPlayers(entity)) {
				Ke2Packets.INSTANCE.sendTo(new PacketEntityTeleport(entity.getEntityId(), posX, posY, posZ), (EntityPlayerMP) player);
			}
		}
		
		this.warping = false;
		this.cooling = true;
		this.cooldownTicksLeft = this.warpCooldownTicks;
		this.setDirty();
	}
}
