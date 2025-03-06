package org.polyfrost.example.testmacro.caculator.functions;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import org.polyfrost.example.testmacro.caculator.utils.Arguments;
import org.polyfrost.example.testmacro.utils.MacroInput;
import org.polyfrost.example.testmacro.utils.MathHelper;

public class Player extends Entity {

    public static ArrayList<MacroInput> inputs = new ArrayList<>();

    public static byte df = 16;

    public int tick = 0;
    public double xOf = 0.0;
    public double zOf = 0.0;
    public double highestZ = 0, lowestZ = 0;
    public double highestX = 0, lowestX = 0;
    public double finalX, finalZ;
    public ArrayList<Double> xCoords = new ArrayList<Double>();
    public ArrayList<Double> zCoords = new ArrayList<Double>();
    public static ArrayList<Double> xresult = new ArrayList<>();
    public static ArrayList<Double> yresult = new ArrayList<>();
    public static ArrayList<Double> zresult = new ArrayList<>();


    public double x = 0.0;
    public double y = 0.0;
    public double z = 0.0;
    public double vx = 0.0;
    public double vy = 0.0;
    public double vz = 0.0;
    public float slip = 0.0F;
    public float last_slip = 0.0F;
    public double multiplier = 0.0;

    public float prevAngle = 0.0F;
    public float angleOffset = 0.0F;
    public float facing = 0.0F;

    public AxisAlignedBB playerbb;

    public Player(World worldIn) {
        super(worldIn);
        this.x = Minecraft.getMinecraft().thePlayer.posX;
        this.z = Minecraft.getMinecraft().thePlayer.posZ;
        this.y = Minecraft.getMinecraft().thePlayer.posY;
        this.facing = Minecraft.getMinecraft().thePlayer.rotationYaw;
        playerbb = new AxisAlignedBB(this.x-0.3D, this.y, this.z-0.3D, this.x+0.3D, this.y+1.8D, this.z+0.3D);
    }


    public void move(Arguments args) {
        int duration = (Integer) args.get("duration");
        boolean airborne = (Boolean) args.get("airborne");
        boolean sprinting = (Boolean) args.get("sprinting");
        boolean sneaking =  (Boolean) args.get("sneaking");
        boolean jumping = (Boolean) args.get("jumping");
        facing +=  (Float) args.get("facing");

        for (int i=1;i<=Math.abs(duration);i++) {

            tick++;
            multiplier = 1;
            float forward = (Integer) args.get("forward");
            float strafing = (Integer) args.get("strafing");
            if (airborne) slip = 1F;
            else slip = (float) args.get("slip");
            if (last_slip == 0F) last_slip = slip;
            angleOffset = facing - prevAngle;
            prevAngle = facing;
            MacroInput tickInput = new MacroInput();
            tickInput.setSprint(sprinting);
            tickInput.setJump(jumping);
            tickInput.setSneak(sneaking);
            tickInput.setAngle(facing);
            if (forward ==1.0F) {
                tickInput.setW(true);
            } else if (forward ==-1.0F) {
                tickInput.setS(true);
            }
            if (strafing ==1.0F) {
                tickInput.setA(true);
            } else if (strafing ==-1.0F) {
                tickInput.setD(true);
            }
            inputs.add(tickInput);

            //this.z += this.vz;
            //this.y += this.vy;
            //this.x += this.vx;
            this.playerbb = this.playerbb.offset(this.vx, this.vy, this.vz);

            xresult.add(this.x);
            yresult.add(this.y);
            zresult.add(this.z);

            //soulsand

            vy -= 0.08D;
            vy *= 0.9800000190734863D;
            if (jumping) vy = 0.42F;

            this.vx *= 0.91F * last_slip;
            this.vz *= 0.91F * last_slip;

            if (Math.abs(this.vz) < 0.005D) this.vz = 0D;
            if (Math.abs(this.vx) < 0.005D) this.vx = 0D;

            float movement = 0;
            if (airborne) {
                movement = 0.02F;
                if (sprinting) movement = (float) (movement + movement * 0.3);
            } else {
                movement = 0.1F;
                if (sprinting) movement = (float) (movement * (1.0D + 0.3F));
                float drag = 0.91F * slip;
                movement *= 0.16277136F / (drag * drag * drag);
            }

            if (sprinting && jumping) {
                float angle = (float) (facing * 0.017453292F);

                this.vz += (0.2F * MathHelper.cos(angle));
                this.vx -= (0.2F * MathHelper.sin(angle));
            }


            if (sneaking) {
                forward = (float) (((float) forward) * 0.3D);
                strafing = (float) (((float) strafing) * 0.3D);
            }

            forward *= 0.98F;
            strafing *= 0.98F;

            float distance = (float) (strafing * strafing + forward * forward);

            if (distance >= 1.0E-4F) {
                distance = (float) Math.sqrt(distance);
                if (distance < 1.0F) distance = 1.0F;

                distance = movement / distance;
                forward = forward * distance;
                strafing = strafing * distance;

                float angle = (float) (facing * 3.14159265358979323846F / 180F);

                this.vx += (float) (strafing * MathHelper.cos(angle) - forward * MathHelper.sin(angle));
                this.vz += (float) (forward * MathHelper.cos(angle) + strafing * MathHelper.sin(angle));
            }

            checkCollision();

            last_slip = slip;

            if (!airborne) { //update momentum if airborne
                this.updateMM();
            }
        }
    }

    public void checkCollision() {
        double originalX = this.vx;
        double originalY = this.vy;
        double originalZ = this.vz;
        List<AxisAlignedBB> list = Minecraft.getMinecraft().thePlayer.worldObj.getCollidingBoundingBoxes(this, this.playerbb.addCoord(vx,vy,vz));
        AxisAlignedBB axisalignedbb =playerbb;

        for (AxisAlignedBB aabb : list) {
            vy =aabb.calculateYOffset(playerbb, vy);
        }
        for (AxisAlignedBB aabb : list) {
            vx =aabb.calculateXOffset(playerbb, vx);
        }
        for (AxisAlignedBB aabb : list) {
            vz =aabb.calculateZOffset(playerbb, vz);
        }
        //_setEntityBBox(playerbb.offset(0.0D,vy,0.0D));
        _resetPosToBB();
        if (originalX != vx) {
            vx = 0;
        }
        if (originalZ != vz) {
            vz = 0;
        }
        if (originalY != vy) {
            vy = 0;
        }
    }

    private void _resetPosToBB() {
        x = (playerbb.minX + playerbb.maxX)/2;
        y= playerbb.minY;
        z = (playerbb.minZ + playerbb.maxZ)/2;
    }
    private void _setEntityBBox(AxisAlignedBB axisalignedbb) {
        this.playerbb = axisalignedbb;
    }

    void updateMM() {
        xCoords.add(xOf);
        zCoords.add(zOf);
    }

    public void updateBounds() {
        xCoords.sort((c1, c2) -> Double.compare(c1, c2));
        zCoords.sort((c1, c2) -> Double.compare(c1, c2));

        xCoords.remove(xCoords.size()-1);
        zCoords.remove(zCoords.size()-1);
        zCoords.remove(0);
        xCoords.remove(0);

        if (!xCoords.contains(finalX)) xCoords.add(finalX);
        if (!zCoords.contains(finalZ)) zCoords.add(finalZ);

        xCoords.sort((c1, c2) -> Double.compare(c1, c2));
        zCoords.sort((c1, c2) -> Double.compare(c1, c2));
        lowestZ = zCoords.get(0);
        highestZ = zCoords.get(zCoords.size()-1);
        lowestX = xCoords.get(0);
        highestX = xCoords.get(xCoords.size()-1);

    }

    public void print() {
        DecimalFormat formatting = new DecimalFormat("#");
        formatting.setMaximumFractionDigits(df);
        this.updateBounds();

        System.out.println("z: " + formatting.format(this.z));
        System.out.println("vz: " + formatting.format(this.vz));
        System.out.println("zmm: " + formatting.format(highestZ - lowestZ));


        System.out.println("x: " + formatting.format(this.x));
        System.out.println("vx: " + formatting.format(this.vx));
        System.out.println("xmm: " + formatting.format(highestX - lowestX));

        System.out.println("y: " + formatting.format(this.y));
        System.out.println("vy: " + formatting.format(this.vy));

        System.out.println("vector: " + formatting.format(Math.hypot(this.vz, this.vx)));
        System.out.println("angle: " + Math.atan2(-this.vx,this.vz) * 180d/Math.PI);




    }

    @Override
    protected void entityInit() {

    }

    @Override
    protected void readEntityFromNBT(NBTTagCompound tagCompund) {

    }

    @Override
    protected void writeEntityToNBT(NBTTagCompound tagCompound) {

    }
}