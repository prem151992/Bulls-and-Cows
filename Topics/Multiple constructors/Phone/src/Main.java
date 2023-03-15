class Phone {

    public String ownerName;
    public String countryCode;
    public String cityCode;
    public String number;

    public Phone(String ownerName, String number) {
        this.ownerName = ownerName;
        this.number = number;
    }

    public Phone(String ownerName, String countryCode, String cityCode, String number) {
        this(ownerName, number);
        this.cityCode = cityCode;
        this.countryCode = countryCode;
    }

}
