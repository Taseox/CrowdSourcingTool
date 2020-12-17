package ch.hegarc.ig.util;

import ch.hegarc.ig.business.Donateur;
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
    final private String CMD_ADDDONATEUR = "add";
    final private String CMD_REMOVEDONATEUR = "remove";


    final private Option OPT_FICHIER = new Option("f", "fichier", true, "nom du fichier");
    final private Option OPT_PROJET = new Option("p", "projet", true, "nom du projet");
    final private Option OPT_NOM = new Option("n", "nom", true, "nom du donateur");
    final private Option OPT_PRENOM = new Option("pr", "prenom", true, "prenom du donateur");
    final private Option OPT_SOMME = new Option("s", "somme", true, "somme donnee");


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
                        String fileName = cmdLine.getOptionValue(OPT_FICHIER.getOpt());
                        System.out.println("Import du fichier " + fileName);
                        try {
                            if (fileName.endsWith(".xml")) {
                                this.projets.addProjets(JaxbUnmarshalling.run(fileName));
                            } else if (fileName.endsWith(".json")) {
                                this.projets.addProjets(JacksonReader.run(fileName));
                            }
                            System.out.println(projets.toString());
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
                        if(projets.getProjectName(projectName) == null) {
                            System.out.println("Le projet " + projectName + "n'existe pas");
                        } else if(projectName.equalsIgnoreCase("All")){
                            System.out.println("Export de tous les projets.");
                            JacksonWriter.run(this.projets.toList(), fileName);
                        }else{
                            JacksonWriter.run(projets.getProjectName(projectName), fileName);
                            System.out.println(projets.getMedianeEtMoyenne(projets.getProjectName(projectName)));
                        }
                    } else {
                        printAppHelp();
                    }
                    break;

                case CMD_STATS:
                    if (cmdLine.hasOption(OPT_FICHIER.getOpt()) && cmdLine.hasOption(OPT_PROJET.getOpt())) {
                        String fileName = cmdLine.getOptionValue(OPT_FICHIER.getOpt());
                        String projectName = cmdLine.getOptionValue(OPT_PROJET.getOpt());
                        try{

                        }catch(Exception E){
                            E.printStackTrace();

                        }
                    } else {
                        printAppHelp();
                    }
                    break;

                case CMD_ADDDONATEUR:

                    if (cmdLine.hasOption(OPT_PROJET.getOpt()) && cmdLine.hasOption(OPT_NOM.getOpt()) && cmdLine.hasOption(OPT_PRENOM.getOpt()) && cmdLine.hasOption(OPT_SOMME.getOpt())){
                        try{
                            String projectName = cmdLine.getOptionValue(OPT_PROJET.getOpt());
                            String nom = cmdLine.getOptionValue(OPT_NOM.getOpt());
                            String prenom = cmdLine.getOptionValue(OPT_PRENOM.getOpt());
                            Long somme = Long.parseLong(cmdLine.getOptionValue (OPT_SOMME.getOpt()));

                            if (projets.getProjectName(projectName)==null) {
                                System.out.println("Projet inexistant!");
                            } else {
                                Donateur donateur = new Donateur();
                                donateur.setNom(nom);
                                donateur.setPrenom(prenom);
                                donateur.setSomme(somme);
                                donateur.setMonnaie("CHF");
                                if (projets.getProjectName(projectName).addDonateur(donateur)) {
                                    System.out.println("Donateur ajouté au projet avec succès!");
                                } else {
                                    System.out.println("Donateur déjà existant");
                                }
                                System.out.println(projets.getProjectName(projectName).toString());
                            }
                        }catch (Exception E){
                            E.printStackTrace();
                        }

                    }else {
                        printAppHelp();
                    }
                    break;

                case CMD_REMOVEDONATEUR:

                    if (cmdLine.hasOption(OPT_PROJET.getOpt()) && cmdLine.hasOption(OPT_NOM.getOpt()) && cmdLine.hasOption(OPT_PRENOM.getOpt())){
                        try{
                            Projet projet = projets.getProjectName(cmdLine.getOptionValue(OPT_PROJET.getOpt()));
                            String nom = cmdLine.getOptionValue(OPT_NOM.getOpt());
                            String prenom = cmdLine.getOptionValue(OPT_PRENOM.getOpt());
                            if(projet.removeDonateur(nom, prenom)) {
                                System.out.println("Donateur supprimé du projet avec succès!");
                            } else {
                                System.out.println("Donateur non existant dans ce projet!");
                            }
                            System.out.println(projets.getProjectName(cmdLine.getOptionValue(OPT_PROJET.getOpt())).toString());
                        }catch (Exception E){
                            E.printStackTrace();
                        }

                    }else {
                        printAppHelp();
                    }
                    break;

                case CMD_EXIT:
                    System.out.println("Fermeture!");
                    running = false;
                    break;

                default:
                    System.out.println("Commande non reconnue!");
                    printAppHelp();
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
        options.addOption(OPT_FICHIER).addOption(OPT_PROJET).addOption(OPT_SOMME).addOption(OPT_PRENOM).addOption(OPT_NOM);
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
        formatter.printHelp(CMD_ADDDONATEUR, new Options().addOption(OPT_PROJET).addOption(OPT_NOM).addOption(OPT_PRENOM).addOption(OPT_SOMME), true);
        formatter.printHelp(CMD_REMOVEDONATEUR, new Options().addOption(OPT_PROJET).addOption(OPT_NOM).addOption(OPT_PRENOM), true);

        formatter.printHelp(CMD_EXIT, new Options());
    }
}
