package uo.sdi.model;

public enum SeatStatus {
    PENDIENTE, ADMITIDO, SIN_PLAZA, EXCLUIDO;

    public boolean isAccepted()
    {
        return this.equals(SeatStatus.ADMITIDO);
    }
}


