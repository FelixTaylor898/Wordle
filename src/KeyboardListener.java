import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardListener implements KeyListener {

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        switch (e.getKeyCode()) {
        case KeyEvent.VK_BACK_SPACE:
            Game.backspace();
            break;
        case KeyEvent.VK_ENTER:
            Game.enter();
            break;
        default:
            if (code >= KeyEvent.VK_A && code <= KeyEvent.VK_Z)
                Game.type(Character.toString((char) code));
            break;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub

    }

}