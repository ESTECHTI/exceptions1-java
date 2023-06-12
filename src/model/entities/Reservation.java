package model.entities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import model.exceptions.DomainException;

public class Reservation {
  
    private Integer roomNumber;
    private Date checkIn;
    private Date checkOut;

    private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");


  public Reservation(Integer roomNumber, Date checkIn, Date checkOut) {
     if (!checkOut.after(checkIn)) {
        //Essa excessão do Java é usada tipicamente quando os argumentos que são passados para o método são inválidos.
				//throw new IllegalArgumentException ("Error in reservation: Check-out date must be after check-in date");
        throw new DomainException( "Error in reservation: Check-out date must be after check-in date");
			} 
    this.roomNumber = roomNumber;
    this.checkIn = checkIn;
    this.checkOut = checkOut;
  }


  public Integer getRoomNumber() {
    return this.roomNumber;
  }

  public void setRoomNumber(Integer roomNumber) {
    this.roomNumber = roomNumber;
  }

  public Date getCheckIn() {
    return this.checkIn;
  }

  public Date getCheckOut() {
    return this.checkOut;
  }

  public long duration() {
      long diff = checkOut.getTime() - checkIn.getTime();
      return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
  }

  public void updateDates(Date checkIn, Date checkOut) {

      Date now = new Date();
			if (checkIn.before(now) || checkOut.before(now)) {
        //Essa excessão do Java é usada tipicamente quando os argumentos que são passados para o método são inválidos.
				//throw new IllegalArgumentException( "Error in reservation: Reservation dates for update must be future dates");
        throw new DomainException( "Error in reservation: Reservation dates for update must be future dates");
			} 
      if (!checkOut.after(checkIn)) {
        //Essa excessão do Java é usada tipicamente quando os argumentos que são passados para o método são inválidos.
				//throw new IllegalArgumentException ("Error in reservation: Check-out date must be after check-in date");
        throw new DomainException( "Error in reservation: Check-out date must be after check-in date");
			} 

      this.checkIn = checkIn;
      this.checkOut = checkOut;
  }

  @Override
  public String toString() {
      return "Room "
      + roomNumber
      + ", check-in: "
      + sdf.format(checkIn)
      + ", check-out: "
      + sdf.format(checkOut)
      + ",  "
      + duration()
      + " nights";
  }

}
