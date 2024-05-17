package com.mygdx.game;

import com.badlogic.gdx.math.Rectangle;

public class Zeppelin {
    private Rectangle bounds;
    private float maxSpeed;
    private float acceleration;
    private float deceleration;
    private float velocityX;
    private float velocityY;
    private float windX;
    private float windY;


    private final float windEffectMultiplier = 5.0f;

    public Zeppelin(float x, float y, float width, float height, float maxSpeed, float acceleration, float deceleration) {
        bounds = new Rectangle(x, y, width, height);
        this.maxSpeed = maxSpeed;
        this.acceleration = acceleration;
        this.deceleration = deceleration;
        this.velocityX = 0;
        this.velocityY = 0;
        this.windX = 0;
        this.windY = 0;
    }

    public void setWind(float windX, float windY) {
        this.windX = windX;
        this.windY = windY;
    }

    public void update(float delta, boolean moveLeft, boolean moveRight, boolean moveUp, boolean moveDown) {
        if (moveLeft) {
            velocityX -= acceleration * delta;
        } else if (moveRight) {
            velocityX += acceleration * delta;
        } else {
            if (velocityX > 0) {
                velocityX -= deceleration * delta;
            } else if (velocityX < 0) {
                velocityX += deceleration * delta;
            }
        }

        if (moveUp) {
            velocityY += acceleration * delta;
        } else if (moveDown) {
            velocityY -= acceleration * delta;
        } else {
            if (velocityY > 0) {
                velocityY -= deceleration * delta;
            } else if (velocityY < 0) {
                velocityY += deceleration * delta;
            }
        }

        // Apply wind effect
        velocityX += windX * windEffectMultiplier * delta;
        velocityY += windY * windEffectMultiplier * delta;

        // Clamp velocities to max speed
        if (velocityX > maxSpeed) velocityX = maxSpeed;
        if (velocityX < -maxSpeed) velocityX = -maxSpeed;
        if (velocityY > maxSpeed) velocityY = maxSpeed;
        if (velocityY < -maxSpeed) velocityY = -maxSpeed;

        bounds.x += velocityX * delta;
        bounds.y += velocityY * delta;
    }

    public Rectangle getBounds() {
        return bounds;
    }
}
