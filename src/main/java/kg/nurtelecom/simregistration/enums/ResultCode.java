package kg.nurtelecom.simregistration.enums;

public enum ResultCode {

    SUCCESS("SUCCESS"),
    FAIL("FAIL"),
    EXCEPTION("EXCEPTION");

    private String title;

    ResultCode(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return title;
    }
}
