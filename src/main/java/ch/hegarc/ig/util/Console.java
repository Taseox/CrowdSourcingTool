package ch.hegarc.ig.util;

import ch.hegarc.ig.business.Projet;
import ch.hegarc.ig.business.ProjetSet;
import ch.hegarc.ig.util.jackson.JacksonReader;
import ch.hegarc.ig.util.jackson.JacksonWriter;
import ch.hegarc.ig.util.jaxb.unmarshalling.JaxbUnmarshalling;
import org.apache.commons.cli.*;

import java.io.IOException;
import java.sql.SQLOutput;
import java.util.Scanner;
import java.util.Set;

public class Console {

    private ProjetSet projets = new ProjetSet();

    final private String CMD_IMPORT = "import";
    final private String CMD_EXPORT = "export";
    final private String CMD_STATS = "stats";
    final private String CMD_EXIT = "exit";

    final private Option OPT_FICHIER = new Option("f", "fichier", true, "nom du fichier");
    final private Option OPT_PROJET = new Option("p", "projet", true, "nom du projet");

    /**
     * Démarre la commande
     */
    public void runCommand() {
        Scanner command = new Scanner(System.in);
        System.out.println("Entrer votre commande: ");

        boolean running = true;
        while (running) {
            String com = command.nextLine();
            String[] arguments = com.split(" ");
            CommandLine cmdLine = parseArguments(arguments);

            switch (cmdLine.getArgs()[0]) {

                case CMD_IMPORT:
                    if (cmdLine.hasOption(OPT_FICHIER.getOpt())) {
                        //TODO : fusion des fichiers
                        String fileName = cmdLine.getOptionValue(OPT_FICHIER.getOpt());
                        System.out.println("Import du fichier " + fileName);
                        try {
                            if (fileName.endsWith(".xml")) {
                                this.projets.addProjets(JaxbUnmarshalling.run(fileName));
                                this.projets.get("Alpha").toString();
                                //System.out.println(projets.toString());
                            } else if (fileName.endsWith(".json")) {
                                this.projets.addProjets(JacksonReader.run(fileName));
                                //System.out.println(projets.toString());
                            }
                        } catch (Exception E){
                            E.printStackTrace();
                        }
                    } else {
                        printAppHelp();
                    }
                    break;

                case CMD_EXPORT:
                    if (cmdLine.hasOption(OPT_FICHIER.getOpt()) && cmdLine.hasOption(OPT_PROJET.getOpt())) {

                        String fileName = cmdLine.getOptionValue(OPT_FICHIER.getOpt());
                        String projectName = cmdLine.getOptionValue(OPT_PROJET.getOpt());
                        // TODO Export du fichier JSON

                        if(projectName.equalsIgnoreCase("All")){
                            System.out.println("Export de tous les projets.");
                            for(Projet p : projets.getList()){
                                JacksonWriter.run(p.getName(),"donations.json");
                            }
                        }else{
                            JacksonWriter.run(projectName, fileName);
                        }
                    } else {
                        printAppHelp();
                    }
                    break;

                case CMD_STATS:

                    // TODO Calcule des stats des projets

                    break;

                case CMD_EXIT:
                    System.out.println("Fermeture!");
                    running = false;
                    break;

                default:
                    System.out.println("Commande non reconnue!");
                    break;
            }
        }
        command.close();
    }

    /**
     * Parses des arguments
     *
     * @param args application arguments
     * @return <code>CommandLine</code> which represents a list of application
     * arguments.
     */
    private CommandLine parseArguments(String[] args) {

        Options options = getAllOptions();
        CommandLine line = null;
        CommandLineParser parser = new DefaultParser();

        try {
            line = parser.parse(options, args);

        } catch (ParseException ex) {

            System.err.println("Erreur dans la lecture des arguments!");
            System.err.println(ex.toString());
            printAppHelp();
        }

        return line;
    }

    /**
     * Generates application command line options
     *
     * @return application <code>Options</code>
     */
    private Options getAllOptions() {

        Options options = new Options();
        options.addOption(OPT_FICHIER).addOption(OPT_PROJET);
        return options;
    }

    /**
     * Prints application help
     */
    private void printAppHelp() {
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp(CMD_IMPORT, new Options().addOption(OPT_FICHIER), true);
        formatter.printHelp(CMD_EXPORT, new Options().addOption(OPT_FICHIER).addOption(OPT_PROJET), true);
        formatter.printHelp(CMD_STATS, new Options().addOption(OPT_PROJET), true);
        formatter.printHelp(CMD_EXIT, new Options());
    }
}
