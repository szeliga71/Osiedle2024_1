package Environment;

import EstateObjects.Garage;
import Items.Item;
import People.Person;

import java.util.*;

public abstract class AddRemoveItem extends LocatorAddRemove {

    public  void addItemToGarage(Set<Item> items, Garage garage)throws TooManyThingsException{

        Optional<Item> itemTemp1 = chooseOptionalItem(items);

        if (itemTemp1.isPresent()) {


                if (itemTemp1.get().getSize() + garage.getVolume() > garage.getArea()){


                throw new TooManyThingsException(" Niewystarczajaca ilosc miejsca w garazu ");

            }else{

            System.out.println("  item DO DODANIA " + itemTemp1.get());
                garage.getItemsInGarage().add(itemTemp1.get());
                items.remove(itemTemp1.get());
                garage.setVolume((long) (itemTemp1.get().getSize()+garage.getVolume()));


        }
    }else{
        System.out.println(" nie mozn awsadzic przedmiotu do garazu ");
        }
    }


    public  void removeItemFromGarage(Set<Item> items,Garage garage){

        Optional<Item>itemTempOp = chooseOptionalItem(garage.getItemsInGarage());

        if(itemTempOp.isPresent()){
        items.add(itemTempOp.get());

        garage.getItemsInGarage().remove(itemTempOp.get());
            garage.setVolume((long) (garage.getVolume()-itemTempOp.get().getSize()));
        System.out.println("  Przedmiot  " + itemTempOp.get() + "  USUNIETY ");}
        else{
            System.out.println(" garaz jest juz pusty  ");
        }
    }

    public List<Item> allItems(Set<Item> items) {
        return new ArrayList<>(items);
    }


    public Optional<Item> chooseOptionalItem(Set<Item> itemsSSet) {
        List<Item> items = allItems(itemsSSet);

        if (items.isEmpty()) {
            System.out.println(" nie ma juz zadnych przedmiotow !");
            return Optional.empty();
        } else {
            System.out.println(" Prosze podac numer pod ktorym znajduje sie przedmiot ,ktory chcesz wybrac  : " + '\n');

            show(items);

            System.out.println(" numer  :");
            int position;
            try {
                position = (Integer.parseInt(choose(scan))) - 1;

            } catch (NumberFormatException e) {
                System.out.println(" wprowadzono nieprawidlowa liczbe , sprobuj ponownie !");
                return Optional.empty();
            }
            if ((position < 0) || (position >= items.size())) {

                return Optional.empty();


            } else {
                return Optional.of(items.get(position));
            }
        }
    }


    public List<Item> itemsInGarage(Garage garage) {
        if (garage == null) {
            return new ArrayList<>();
        } else {
            if (garage.getItemsInGarage().isEmpty()) {
                System.out.println(" Uzytkownik nie posiada zadnych przedmiotow w tym garazu ");
            }
            return new ArrayList<>(garage.getItemsInGarage());
        }
    }

    public List<Item> allItemsOfUser(Person user) {

        List<Item> items = new ArrayList<>();

        List<Garage> tempGarage = roomsOfUser(Garage.class, user).stream().toList();

        for (Garage g : tempGarage) {
            items.addAll(g.getItemsInGarage());

        }
        if (items.isEmpty()) {
            System.out.println(" uzytkownik nie posiada jeszcze zadnych przedmiotow ");
        }

        return items;
    }
}

