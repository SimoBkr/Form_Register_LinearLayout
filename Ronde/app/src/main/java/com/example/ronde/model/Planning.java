package com.example.ronde.model;

public class Planning {

        private long id;
        private String NamePlanning;
        private String DatePlanning;
        private String heure;
        private Personne agent;

        public Planning() {
        }

        public Planning(long id, String namePlanning, String datePlanning, String heure, Personne agent) {
            this.id = id;
            NamePlanning = namePlanning;
            DatePlanning = datePlanning;
            this.heure = heure;
            this.agent = agent;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getNamePlanning() {
            return NamePlanning;
        }

        public void setNamePlanning(String namePlanning) {
            NamePlanning = namePlanning;
        }

        public String getDatePlanning() {
            return DatePlanning;
        }

        public void setDatePlanning(String datePlanning) {
            DatePlanning = datePlanning;
        }

        public String getHeure() {
            return heure;
        }

        public void setHeure(String heure) {
            this.heure = heure;
        }

        public Personne getAgent() {
            return agent;
        }

        public void setAgent(Personne agent) {
            this.agent = agent;
        }
    }


