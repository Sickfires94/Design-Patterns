interface State {
    void next(StatefulVendingMachine statefulVendingMachine, Enum<Actions> action);
}

class idleState implements State {
    public void next(StatefulVendingMachine statefulVendingMachine, Enum<Actions> action) {
        switch (action){
            case Actions.add_money -> {
                statefulVendingMachine.state = new displayAmountState();
                statefulVendingMachine.state.next(statefulVendingMachine, Actions.nothing);
            }
            default -> {
                System.out.println("Add Money to Purchase Item");
            }
        }
    }
}

class displayAmountState implements State {
    public void next(StatefulVendingMachine statefulVendingMachine, Enum<Actions> action) {
        System.out.println("Money Added");
        statefulVendingMachine.state = new waitForSelectionState();
        System.out.println("Please Select Item");
    }
}

class waitForSelectionState implements State {
    public void next(StatefulVendingMachine statefulVendingMachine, Enum<Actions> action) {
        switch (action){
            case Actions.select_item -> {
                System.out.println("Item Selected");
                statefulVendingMachine.state = new productSelectedState();
                statefulVendingMachine.state.next(statefulVendingMachine, Actions.nothing);
            }
            case Actions.cancel -> {
                System.out.println("Transaction Cancelled");
                statefulVendingMachine.state = new refundChangeState();
                statefulVendingMachine.state.next(statefulVendingMachine, Actions.nothing);
            }
            case Actions.add_money -> {
                System.out.println("Can not add additional money, select item to Purchase");
            }
            default -> {
                System.out.println("Select Item to Purchase");
            }
        }

    }
}

class productSelectedState implements State {
    public void next(StatefulVendingMachine statefulVendingMachine, Enum<Actions> action) {
        System.out.println("Dispensing Item");
        statefulVendingMachine.state = new dispenseState();
        statefulVendingMachine.state.next(statefulVendingMachine, Actions.nothing);

    }
}

class dispenseState implements State {
    public void next(StatefulVendingMachine statefulVendingMachine, Enum<Actions> action) {
        System.out.println("Item Dispensed");
        statefulVendingMachine.state = new refundChangeState();
        statefulVendingMachine.state.next(statefulVendingMachine, Actions.nothing);
    }
}

class refundChangeState implements State {
    public void next(StatefulVendingMachine statefulVendingMachine, Enum<Actions> action) {
        System.out.println("Refunding remaining amount");
        System.out.println("*************************************");
        statefulVendingMachine.state = new idleState();
        statefulVendingMachine.state.next(statefulVendingMachine, Actions.nothing);
    }
}
