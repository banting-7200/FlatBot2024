// Address //
package frc.robot.Lights;
// Imports //
import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj.util.Color;
// Base Class //
public class Lights
{
    // Data //
    private AddressableLED LED;
    private AddressableLEDBuffer LEDBuffer;

    public int rainbowFirstPixelHue = 0;
    // Constructor //
    public Lights(int port, int length) {
        LED = new AddressableLED(port);
        LEDBuffer = new AddressableLEDBuffer(length);

        LED.setLength(length);
        LED.start();
    }

    // Base Methods //
    public void lightsPeriodic() {
        LED.setData(LEDBuffer);
    }

    public void off() {
        // Deactivate All LEDS //
        for(int i = 0; i < LEDBuffer.getLength(); i++)
            LEDBuffer.setLED(i, Color.kBlack);
    }
        
    public void solid(Color colour) {
        for(int i = 0; i<LEDBuffer.getLength(); i++){
            LEDBuffer.setLED(i, colour);
        }
    }
    
    public void inPattern(Color[] lightPattern)
    {
        // Example: Color[] lightPattern = {Color.kRed, Color.kGreen, Color.kWhite};

        for (int i = 0; i < LEDBuffer.getLength(); i++)
            LEDBuffer.setLED(i, lightPattern[i % lightPattern.length]);
    }

    public void rainbow() {
        // Rainbow Pattern // 
        for (int i = 0; i < LEDBuffer.getLength(); i++) {
            int hue = (rainbowFirstPixelHue + ( i * 180 / LEDBuffer.getLength()) % 180);
            LEDBuffer.setHSV(i, hue, 255, 128);
        }
        
        rainbowFirstPixelHue += 3;
        rainbowFirstPixelHue %= 180;
    }
    // Main Method //
    public static void main()
    {
        
    }

}