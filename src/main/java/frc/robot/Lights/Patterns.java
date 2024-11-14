// Address //
package frc.robot.Lights;

// Imports //
import edu.wpi.first.wpilibj.util.Color;

// Base Class //
public class Patterns {
    public Lights LED;
    private int effect;
    private int delay;
    private int count;

    // Constructor //
    public Patterns(Lights LED) {
        this.LED = LED;
    }

    public void patternPeriodic(){
                // Activate Effect //
        switch (effect) {
            case 0:
                LED.off();
                break;
            case 1:
                LED.rainbow();
                break;
            case 2:
                candyCane();
                break;
            case 3:
                candyCane2();
                break;
            case 4:
                ikea();
                break;
            case 5:
                snowTree();
                break;
            case 6:
                presentWrapping();
                break;
            case 7:
                salmon();
                break;
            case 8:
                acidGreen();
                break;
            case 9:
                ice();
                break;
            case 10:
                pink();
                break;
            default:
                effect = 0;
                break;

        }
        LED.lightsPeriodic();
        count++;
        delay = (int)Math.floor(count/50);
    }

    public void cycleEffect() {
        // Switches to next effect //
        effect++;
    }

    // Patterns //
    public void candyCane() {
        LED.inPattern(new Color[] { Color.kRed, Color.kWhite },delay);
    }

    public void candyCane2() {
        LED.inPattern(
                new Color[] { Color.kDarkRed, Color.kRed, Color.kDarkRed, Color.kGray, Color.kWhite, Color.kGray },delay);
    }

    public void ikea() {
        LED.inPattern(new Color[] { Color.kYellow, Color.kBlue },delay);
    }

    public void crimson() {
        LED.inPattern(new Color[] { Color.kRed, Color.kDarkRed, Color.fromHSV(0, 73, 100)},delay);
    }

    public void snowTree() {
        LED.inPattern(new Color[] { Color.kWhite, Color.kLightGreen },delay);
    }

    public void presentWrapping() {
        LED.inPattern(new Color[] { Color.fromHSV(134, 45, 41), Color.fromHSV(97, 48, 61), Color.fromHSV(76, 56, 78),
                Color.fromHSV(47, 14, 95), Color.fromHSV(357, 60, 74) },delay);
    }

    public void salmon() {
        LED.inPattern(new Color[] { Color.fromHSV(6, 54, 98) },delay);
    }

    public void acidGreen() {
        LED.inPattern(new Color[] { Color.fromHSV(90, 90, 100), Color.fromHSV(120, 100, 33), Color.fromHSV(120, 100, 60)},delay);
    }

    public void ice() {
        LED.inPattern(new Color[] { Color.fromHSV(180, 61, 100), Color.kWhite },delay);
    }

    public void pink() {
        LED.inPattern(new Color[] { Color.fromHSV(301, 87, 100), Color.fromHSV(283, 65, 94), Color.fromHSV(300, 100, 100)},delay);
    }
}