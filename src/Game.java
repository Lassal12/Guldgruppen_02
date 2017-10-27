
import java.util.ArrayList;



/**
 * @author  Michael Kolling and David J. Barnes
 * @version 2006.03.30
 */
public class Game 
{
    private Parser parser;
    private Room currentRoom;
    /*
    Rooms placere vi udenfor 'createRoom' metoden,
    således vi kan tilgå rummene i andre metoder senere.
    fx drop/pickup metoder.
    */
    Room swag_city, randers, johnny_bravo, mors_hus, gulddreng, bjarne_riis, diskotekets_dør, diskoteket, sidney_lee, hall_fame, ole_henriksen, michael_jackson;
    //Vi opretter en ArrayList til at indeholde vores ting som ligger i inventory.
    ArrayList<Swag> inventory = new ArrayList<Swag>();
        
    public Game() 
    {
        createRooms();
        parser = new Parser();
    }

    private void createRooms()
    {
        swag_city = new Room("Du er ved Swag City byskiltet");
        randers = new Room("Du er ved Randers, hjemstedet for Mokaien");
        mors_hus = new Room("Du er hjemme ved mor");
        gulddreng = new Room("Lyden af højt musik. Du er hos Gulddrengen");
        bjarne_riis = new Room("Så går det stærkt, du er hos Bjarne Riis");
        diskotekets_dør = new Room("Høj lyd af bass, festen venter bag diskotekets dør");
        diskoteket = new Room("BOOM BOOM WOOP WOOP PARTY PARTY, du er på diskoteket");
        sidney_lee = new Room("Lugten af selvbruner & foundation fylder lokalet");
        hall_fame = new Room("Du har besejret Sidney Lee, du er en sand cremerider helt");
        ole_henriksen = new Room("Du ser stramme jeans og en tanktop spændt op til lir, totalt fabulous. Hvem mon det er?");
        michael_jackson = new Room("Bam! You've been struck by a smooth criminal");
        johnny_bravo = new Room("Hih hah huh, Johnny Bravo");
        
        swag_city.setExit("east", randers);
        swag_city.setExit("south", johnny_bravo);
        swag_city.setExit("north", diskotekets_dør);

        randers.setExit("west", swag_city);
        randers.setExit("south", mors_hus);

        mors_hus.setExit("north", randers);
        mors_hus.setExit("west", johnny_bravo);

        johnny_bravo.setExit("north", swag_city);
        johnny_bravo.setExit("east", mors_hus);
        johnny_bravo.setExit("west", michael_jackson);

        michael_jackson.setExit("east", johnny_bravo);
        michael_jackson.setExit("north", ole_henriksen);
        
        ole_henriksen.setExit("south", michael_jackson);
        
        diskotekets_dør.setExit("south", swag_city);
        diskotekets_dør.setExit("east", gulddreng);
        diskotekets_dør.setExit("north", diskoteket);
        
        gulddreng.setExit("west", diskotekets_dør);
        gulddreng.setExit("north", bjarne_riis);
        
        bjarne_riis.setExit("south", gulddreng);
        
        diskoteket.setExit("south", diskotekets_dør);
        diskoteket.setExit("west", sidney_lee);
        
        sidney_lee.setExit("east", diskoteket);
        sidney_lee.setExit("south", hall_fame);

        currentRoom = swag_city;
        
        inventory.add(new Swag("Swag håndtegn"));
        
        //Swag tingene indsættes i de forskellige rum.
        johnny_bravo.setSwag(new Swag("Bravo håret"));
        johnny_bravo.setSwag(new Swag("Guldmønter"));
        michael_jackson.setSwag(new Swag("Guld sko"));
        gulddreng.setSwag(new Swag("Guldkæden"));
        bjarne_riis.setSwag(new Swag("Hurtig briller"));
        ole_henriksen.setSwag(new Swag("Fabulous tøj"));
        
        //NPC'er indsættes i de forskellige rum.
        johnny_bravo.setNPC("Johnny Bravo", "HU HA HI, Johnny Bravo!");
        michael_jackson.setNPC("Michael Jackson", "\u266A\u266A\u266A Annie are you ok? Are you ok, Annie...\u266A\u266A\u266A");
        gulddreng.setNPC("Gulddreng", "\u266A\u266A\u266A Er du model? Vil du med på hotel? \u266A\u266A\u266A");
        bjarne_riis.setNPC("Bjarne Riis", "Cykle, cykle, cykle. Ikke tænk på EPO!");
        ole_henriksen.setNPC("Ole Henriksen", "I'm sooo fabolous.");
        mors_hus.setNPC("Mor", "Velkommen hjem søn!");
        sidney_lee.setNPC("Sidney Lee", "Jeg er forlækker til love!");
        diskotekets_dør.setNPC("Dørmand", "Holdt holdt holdt! Ingen adgang på diskuteket med en så lav swag-promille.");
    }

    public void play() 
    {            
        printWelcome();

        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Tak fordi at du spillede med os, din stodder.");
    }

    private void printWelcome()
    {
        //Intro til spillet
        System.out.println("Velkommen til Swag City!");
        System.out.println("Byen hvor drenge bliver til mænd... eller noget.");
        System.out.println("\n   ----- Introduktion til spillet -----   \n");
        System.out.println("Erik Deluxe er havnet i Swag City.");
        System.out.println("Han er på en mission for at finde byens mest swagste person.");
        System.out.println("På Eriks farefulde færd møder han diverse hjælpere og modstandere,");
        System.out.println("disse kan henholdsvis hjælpe med tips til at vinde over Sidney Lee,");
        System.out.println("som er byens swagster, eller frarøve dig dine swagting.");
        System.out.println("Swagtingene kan opnås fra forksellige bekendtheder som befinder sig i byen,");
        System.out.println("der skal mindst opnås 3 yderlige swagting for at få adgang til byens diskotek,");
        System.out.println("som er opholdsstedet for Sidney Lee.");
        System.out.println("Som start har Erik Deluxe hans verdensberømte swag håndtegn,");
        System.out.println("disse swagting bruges som spillets liv, frarøves alle swagtingene taber man spillet,");
        System.out.println("og for at vinde spillet skal man besejre Sidney Lee i en dancebattle,");
        System.out.println("for at blive byens nye mest swagste person.\n\n");
        System.out.println("Har du brug for hjælp? Skriv '" + CommandWord.HELP + "' hvis du er fuld.\n");
        System.out.println();
        System.out.println(currentRoom.getLongDescription());
    }

    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        CommandWord commandWord = command.getCommandWord();

        if(commandWord == CommandWord.UNKNOWN) {
            System.out.println("Æhhh? Hvad fanden?\n");
            return false;
        }
        //Kommandoer som bruges til at spille spillet.
        if (commandWord == CommandWord.HELP) {
            printHelp();
        }
        else if (commandWord == CommandWord.GO) {
            wantToQuit = goRoom(command);
        }
        else if (commandWord == CommandWord.QUIT) {
            wantToQuit = quit(command);
        }
        else if (commandWord == CommandWord.LOOK) {
            printLook();
        }
        else if (commandWord == CommandWord.INVENTORY) {
            printInventory();
        }
        /*
        der blevet lavet en ny kommando med Get så der kan pickes items up 
        */
        else if (commandWord == CommandWord.GET) {
            getSwag(command);
        }
        else if (commandWord == CommandWord.INTERACT) {
            interactNPC(command);
        }
        return wantToQuit;
    }
 
    /*
    I relation med Get fukntionen skal der implementeres commandoen således at det kan fungerer inden i spillet. 
    */
    private void getSwag(Command command) 
    {
        if(!command.hasSecondWord()) {
            System.out.println("Get what?");
            return;
        }

        String Swag = command.getSecondWord();
        Swag newSwag = currentRoom.getSwag(Swag);
        
        if (newSwag == null) {
            System.out.println("  That item is not there ");
        }
        else {
            inventory.add(newSwag); 
            currentRoom.removeSwag(Swag);
            System.out.println("Picked up" + Swag);
        }
    }

    private void printHelp() 
    {
        System.out.println("Du helt væk, mokaiens dunst sværmer omkring dig.");
        System.out.println("Tag dig sammen.\n");
        System.out.println("Dine råb om hjælp er:");
        parser.showCommands();
        System.out.println();
    }
    
    private void printLook()
    {
        //Metode til at inspicere et rum.
        if (currentRoom == swag_city) {
            System.out.println("Du ser en shady type hænge ud ved byskiltet");
            System.out.println("Der ligger et par mønter på vejen\n");
        }
        else if (currentRoom == randers) {
            System.out.println("Der ligger mokaï strøget udover det hele.");
            System.out.println("Du ser et par fyre fikse deres knallerter\n");
        }
        else if (currentRoom == mors_hus) {
            System.out.println("Din mor stirrer hysterisk på dig.");
            System.out.println("Hun ser ikke ud til at kunne lide din swag\n");
        }
        else if (currentRoom == johnny_bravo) {
            System.out.println("Du ser den mest mandlige mandemand stirre dig i øjnene");
            System.out.println("Der ligger et par mønter, nok fra den sidste dulle han tog\n");
        }
        else if (currentRoom == michael_jackson) {
            System.out.println("Kongen af pop lever stadig, hans død var bare fake news");
            System.out.println("du ser et par ældgamle mønter liggende på en sten\n");
        }
        else if (currentRoom == ole_henriksen) {
            System.out.println("Ole Henriksen står fremme i al sin fabulousness");
            System.out.println("Han kigger nysgerrigt på dig, som en løve.");
            System.out.println("...");
            System.out.println("Hvis en løve var gay\n");            
        }
        else if (currentRoom == diskotekets_dør) {
            System.out.println("Dørmanden kigger surt på dig");
            System.out.println("Det er kun de mest swagste folk, som bliver lukket ind");
            System.out.println("Der ligger et par mønter på jorden\n");           
        }
        else if (currentRoom == gulddreng) {
            System.out.println("Fanfaren af piger skriger højere end gulddreng synger");
            System.out.println("Han ser ud til at mangle noget\n");            
        }
        else if (currentRoom == bjarne_riis) {
            System.out.println("Du er ude på landet. Det eneste, du kan se er Bjarne Riis' skygge");
            System.out.println("Resten af manden er allerede langt over alle bakker\n");
        }
        else if (currentRoom == diskoteket) {
            System.out.println("Der er fuckboys og duller galore");
            System.out.println("Der sidder en lækker lille sag oppe ved baren, måske skulle man snakke med hende?");
            System.out.println("Der sidder en rigtig makker nede ved et af bordene. Han vinker dig hen\n");
        }
        else if (currentRoom == sidney_lee) {
            System.out.println("Sidney Lee står og smørrer sig ind i bruncreme,");
            System.out.println("han virker klar til en gang dans\n");
        }
    }

    private boolean goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            System.out.println("Hvor vil du hen Erik?");
            System.out.println(currentRoom.getMediumDescription() + "\n");
            return false;
        }

        String direction = command.getSecondWord();

        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("Bum! Du løb ind i en væg, drink noget mindre\n");
        }
        else {
            currentRoom = nextRoom;
            System.out.println(currentRoom.getLongDescription());
            //if statement som sætter wantToQuit = true, hvilket gør at man vinder spillet.
            if (currentRoom == hall_fame) {
                System.out.println("Du er officielt den mest swagste person!");
                System.out.println("Byen er deres o'høje Erik Deluxe.\n");
                return true;
            }
        }
        return false;
    }

    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Prøver du at stoppe med at spille!?\n");
            return false;
        }
        else {
            return true;
        }
    }

    //Printer ArrayListen inventory's indhold til skærmen.
    private void printInventory() {
        String output = "";
        for (int i = 0; i < inventory.size(); i++) {
            output += inventory.get(i).getSwagDescription() + " ";
        }
        System.out.println("Dine swagting:");
        System.out.println(output);
    }
    
    private void interactNPC(Command command) {
        if(!command.hasSecondWord()) {
          System.out.println("Hvem prøver du at kontakte?\n");
        }
        else if (currentRoom == johnny_bravo && command.checkSecondWord("johnny bravo").equalsIgnoreCase("johnny bravo")) {
            // && command.checkSecondWord("johnny bravo")
            //if (command.checkSecondWord("johnny bravo")) {
            System.out.println("jb");
            //}
        }
        else if (currentRoom == michael_jackson) {
            System.out.println("mj");
        }
        else if (currentRoom == gulddreng) {
            System.out.println("gd");
        }
        else if (currentRoom == bjarne_riis) {
            System.out.println("br");
        }
        else if (currentRoom == ole_henriksen) {
            System.out.println("oh");
        }
        else if (currentRoom == mors_hus) {
            System.out.println("mor");
        }
        else if (currentRoom == sidney_lee) {
            System.out.println("sl");
        }
        else if (currentRoom == diskotekets_dør) {
            System.out.println("dørmand");
        }
        else {
            System.out.println("Hvem prøver du at kontakte?\n");
        }
    }
}
