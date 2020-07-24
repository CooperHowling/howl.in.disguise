package howl.in.disguise;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

class BattleSim {

    String fight(List<Transformer> fighters) {

        String result = "";
        String autobotSurvivors = "";
        String decepticonSurvivors = "";
        int autobotWins = 0;
        int decepticonWins = 0;
        int battleCount = 0;

        Predicate<Transformer> isAutoBot = transformer -> transformer.getAllegiance().contains("Autobot");
        Predicate<Transformer> isDecepticon = transformer -> transformer.getAllegiance().contains("Decepticon");

        ArrayList<Transformer> autobots = removeEnemy(fighters, isDecepticon);
        ArrayList<Transformer> decepticons = removeEnemy(fighters, isAutoBot);

        //sort by rank
        Collections.sort(autobots);
        Collections.sort(decepticons);

        int teamSizeDifference = Math.abs(autobots.size() - decepticons.size());
        int numberOfBattles = Math.min(autobots.size(), decepticons.size());
        if(numberOfBattles == 0){
            return "There are no battles on this day...";
        }

        //battle autobots[i] against decepticons[i]
        for (int i=0; i < numberOfBattles; i++){

            //FIGHT!
            if((autobots.get(i).getName().equals("Optimus Prime")) && (decepticons.get(i).getName().equals("Predaking"))){
                //everything is destroyed
                return "All were destroyed... There are no winners or survivors...";
            }else if(autobots.get(i).getName().equals("Optimus Prime")){
                autobotWins++;
            }else if(decepticons.get(i).getName().equals("Predaking")) {
                decepticonWins++;
            }else if(fightOrFlight(autobots, decepticons, i)){
                autobotWins++;
                decepticonSurvivors+=decepticons.get(i).getName()+" ";
            }else if(fightOrFlight(decepticons, autobots, i)){
                decepticonWins++;
                autobotSurvivors+=autobots.get(i).getName()+" ";
            }else if(autobots.get(i).getOverallPower() > decepticons.get(i).getOverallPower()){
                autobotWins++;
            }else if(autobots.get(i).getOverallPower() < decepticons.get(i).getOverallPower()){
                decepticonWins++;
            }else{
                //tie, both destroyed
            }
            //Battle complete
            battleCount++;

        }
        //fighting is over
        if(autobotWins > decepticonWins){
            result = winResults(result, decepticonSurvivors, decepticons, autobots, teamSizeDifference, "\r\n  |  Winning Team (Autobots): ", "\r\n  |  Survivors from the losing team (Decepticons): ");
        }else{
            result = winResults(result, autobotSurvivors, autobots, decepticons, teamSizeDifference, "\r\n  |  Winning Team (Decepticons): ", "\r\n  |  Survivors from the losing team (Autobots): ");
        }

        return battleCount + " battle(s) " + result;
    }

    private String winResults(String result, String survivors, ArrayList<Transformer> losingTransformers, ArrayList<Transformer> winningTransformers, int teamSizeDifference, String winString1, String winString2) {
        String winners = "";

        for (Transformer transformer : winningTransformers) {
            winners += transformer.getName() + " ";
        }
        for (int i = losingTransformers.size(); i > (losingTransformers.size() - teamSizeDifference); i--) {
            survivors += losingTransformers.get(i-1).getName() + " ";
        }

        result += winString1 + winners;
        result += winString2 + survivors;
        return result;
    }

    private boolean fightOrFlight(ArrayList<Transformer> attacker, ArrayList<Transformer> defender, int i) {
        return ((attacker.get(i).getCourage()-4 >= defender.get(i).getCourage()) &&
                attacker.get(i).getStrength()-3 >= defender.get(i).getStrength()) ||
                attacker.get(i).getSkill()-3 >= defender.get(i).getSkill();
    }

    private ArrayList<Transformer> removeEnemy(List<Transformer> fighters, Predicate<Transformer> isTypeToRemove) {
        ArrayList<Transformer> transformersSingleAllegiance = new ArrayList<Transformer>(fighters);
        transformersSingleAllegiance.removeIf(isTypeToRemove);
        return transformersSingleAllegiance;
    }

}
