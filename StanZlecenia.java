public enum StanZlecenia {

        PLANOWANE(false),
        NIEPLANOWANE(false),
        REALIZOWANE(false),
        ZAKONCZONE(false);

        boolean stanZlecenia;

        private StanZlecenia(boolean stanZlecenia) {
            this.stanZlecenia = stanZlecenia;
        }
}
