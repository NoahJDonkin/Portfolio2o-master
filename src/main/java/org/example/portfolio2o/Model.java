/*package org.example.portfolio2o;

import java.util.Arrays;
import java.util.List;

public class Model {

    // Returnerer en liste over bachelorprogrammer
    public List<String> baseProgram() {
        return Arrays.asList("NatBach", "HumTek");
    }

    // Returnerer en liste over fagmoduler
    public List<String> subjectModule() {
        return Arrays.asList("Computer Science", "Informatik", "Astrology");
    }

    // Returnerer en liste over kurser og projekter baseret på det valgte bachelorprogram
    public List<String> baseCourse(String base) {
        if (base.equals("NatBach")) {
            return Arrays.asList(
                    "BK1 Empirical Data (ECTS: 5)",
                    "BK2 Experimental Methods (ECTS: 5)",
                    "BK3 Theory of Natural Science (ECTS: 5)",
                    "Logic and Discrete Mathematics (ECTS: 10)",
                    "Functional Biology – Zoology (ECTS: 5)",
                    "Linear Algebra (ECTS: 10)",
                    "Organic Chemistry (ECTS: 5)",
                    "Biological Chemistry (ECTS: 5)",
                    "Statistical Models (ECTS: 5)",
                    "Functional Programming and Language Implementations (ECTS: 10)",
                    "Classical Mechanics (ECTS: 10)",
                    "Environmental Science (ECTS: 5)",
                    "Cell Biology (ECTS: 5)",
                    "Functional Biology – Botany (ECTS: 5)",
                    "Supplementary Physics (ECTS: 5)",
                    "Calculus (ECTS: 10)",
                    "The Chemical Reaction (ECTS: 5)",
                    "Scientific Computing (ECTS: 5)",
                    "Energy and Climate Changes (ECTS: 5)"
            );
        } else if (base.equals("HumTek")) {
            return Arrays.asList(
                    "Design og Konstruktion I+Workshop (ECTS: 10)",
                    "Subjektivitet, Teknologi og Samfund I (ECTS: 5)",
                    "Teknologiske systemer og artefakter I (ECTS: 5)",
                    "Videnskabsteori (ECTS: 5)",
                    "Design og Konstruktion II+Workshop (ECTS: 10)",
                    "Subjektivitet, Teknologi og Samfund II (ECTS: 5)",
                    "Bæredygtige teknologier (ECTS: 5)",
                    "Kunstig intelligens (ECTS: 5)",
                    "Medier og teknologi - datavisualisering (ECTS: 5)",
                    "Teknologiske Systemer og Artefakter II - Sundhedsteknologi (ECTS: 10)",
                    "Den (in)humane storby (ECTS: 5)",
                    "Interactive Design in the Field (ECTS: 10)",
                    "Organisation og ledelse af designprocesser (ECTS: 5)"
            );
        }
        return null;
    }

    // Returnerer en liste over projekter baseret på det valgte bachelorprogram
    public List<String> baseProject(String base) {
        return Arrays.asList("BP1 " + base + " (ECTS: 15)", "BP2 " + base + " (ECTS: 15)", "BP3 " + base + " (ECTS: 15)", "Bachelorproject " + base + " (ECTS: 15)");
    }

    // Returnerer en liste over kurser for et valgt fagmodul
    public List<String> subjectCourse(String subject) {
        if (subject.equals("Computer Science")) {
            return Arrays.asList(
                    "Essential Computing (ECTS: 5)",
                    "Software Development (ECTS: 10)",
                    "Interactive Digital Systems (ECTS: 10)"
            );
        } else if (subject.equals("Informatik")) {
            return Arrays.asList(
                    "Organisatorisk forandring og IT (ECTS: 5)",
                    "BANDIT (ECTS: 10)",
                    "Interactive Digital Systems (ECTS: 10)"
            );
        } else if (subject.equals("Astrology")) {
            return Arrays.asList(
                    "Essential Astrology (ECTS: 5)",
                    "Venus studies (ECTS: 5)",
                    "Mars studies (ECTS: 5)",
                    "Ascendant calculations (ECTS: 10)"
            );
        }
        return null;
    }

    // Returnerer projektet for et fagmodul (alle projekter er 15 ECTS)
    public String subjectProject(String subject) {
        return "Subject module project in " + subject + " (ECTS: 15)";
    }

    // Returnerer ECTS-pointene for et kursus eller projekt
    public int courseWeight(String course) {
        if (course.contains("(ECTS: 15)")) {
            return 15;
        } else if (course.contains("(ECTS: 10)")) {
            return 10;
        }
        return 5;
    }

    // Tjekker om et element er et projekt
    public boolean isProject(String s) {
        for (String fm : subjectModule()) {
            if (s.equals(subjectProject(fm))) return true;
        }
        for (String bs : baseProgram()) {
            if (baseProject(bs).contains(s)) return true;
        }
        return false;
    }
}
 */
