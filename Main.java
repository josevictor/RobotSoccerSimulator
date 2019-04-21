import processing.core.PVector;
import br.unifor.si.rosos.*;
import br.unifor.si.rosos.testeTeam.TesteTeam;
import processing.core.PApplet;

public class Main extends PApplet{

    GameController controller;

    float SCALE = 300f;

    public static void main(String[] args) {
        PApplet.main("Main");
    }

    public void settings(){
        controller = new GameController(new Match(
                // Team A Class
                TesteTeam.class,
                // Team B Class
                CustomEmptyTeam.class,
                //CustomEmptyTeam.class,
                // Number of robots on each side
                1

        ));

        //controller.getSimulator().setFieldSize(2.44f, 1.82f);
        //controller.getSimulator().setFieldSize(1.05f, 0.68f);
        controller.getSimulator().setFieldSize(3.15f, 2.04f);
        controller.resetGame();
        size((int)controller.getWidth(SCALE) + 200, (int)controller.getHeight(SCALE) + 100);

    }

    public void draw(){
        background(255);
        controller.run();
        translate(100, 0);
        controller.draw(this, SCALE);
        translate(-100, 0);
    }

    /*
        Finds out what is closer to the ball that can be moved,
        and then move to that position
    */
    public void mouseDragged(){
        // Checks what is closest to the mouse cursos (Robots and Ball)
        PVector mousePoint = new PVector((mouseX - 100) / SCALE, (mouseY - 150) / SCALE);

        float closestDist = 0.1f;
        Simulatable closest = controller.getSimulator().ball;

        for(Simulatable s: controller.getSimulator().simulatables){
            // Skip if not Ball or Robot
            if(!(s instanceof Ball || s instanceof Robot))
                continue;

            float dist = PVector.sub(s.getRealPosition(), mousePoint).mag();
            if(closestDist > dist){
                closestDist = dist;
                closest = s;
            }
        }

        if(closest != null){
            closest.getPosition().set(mousePoint);
            closest.setSpeed(new PVector());
            closest.setAccel(new PVector());
        }
    }

    public void keyPressed(){

        if(key == ' '){
            if(!controller.hasStarted()){
                System.out.println("Start game");
                controller.resetGame();
                controller.resumeGame();
            }else if(controller.isRunning()){
                System.out.println("Pause game");
                controller.pauseGame();
            }else{
                System.out.println("Resume game");
                controller.resumeGame();
            }
        }else if(key == 'i'){
            controller.resetGame();
            controller.resumeGame();
        }else if(key == 'r'){
            controller.restartPositions(null);
        }else if(key == 'd'){
            String debug = "DEBUG:";
            debug += "\nisRunning:"+controller.isRunning();
            debug += "\nController Robots:"+controller.robots.size();
            for(Robot r:controller.robots)
                debug += "\n\t"+r+" ["+r.getPosition().x+","+r.getPosition().y+"]";

            debug += "\nSimulatables:"+controller.getSimulator().simulatables.size();
            for(Simulatable r:controller.getSimulator().simulatables)
                debug += "\n\t"+r;

            System.out.println(debug);
        }
    }
}