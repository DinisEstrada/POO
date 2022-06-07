# Teste 20-21

Proposta de Resolucao

Avaliação: Teste de Programacao Orientada aos Objectos (A) MiEI e LCC - DI/UMinho 21/05/2021

by [Dinis Estrada](https://github.com/DinisEstrada)

## Parte 2

### Ex.6

```java
public class CasaInteligente {
    private Collection<SmartDevice> devices;
    private Map<String,Collection<SmartDevice>> locations;

    public CasaInteligente(Collection<SmartDevice> devices) {
        this.devices = devices.stream()
                                .map(SmartDevice::clone)
                                .collect(Collectors.toList());
    }
}

```

### Ex.8

```java
private static class SmartDeviceNotFoundException extends Exception{};

public void remove(String id) throws SmartDeviceNotFoundException {
    SmartDevice d = this.devices.stream()
                                .filter(s -> s.getID().equals(id))
                                .findAny()
                                .orElseThrow(SmartDeviceNotFoundException::new);
    this.devies.remove(d);
    this.locations.values().forEach(v -> v.remove(d));

}
```

### Ex.9

```java
public class SmartDevice implements Comparator<SmarDevide> {
    //...
    // devolve o consumo desde o inicio
    public double totalConsumo() {...}
    //...
    
    public int compareTo(SmartDevice sd) {
        return (int) (this.totalConsumo() - sd.totalConsumo())
    }

    //...

}
```

```java
public Iterator<SmartDevice> devicesPorConsumoCrescente() {
    return this.devices.stream()
                        .sort((a,b) -> a.compareTo(b))
                        .iterator();
}
```


### Ex.10

```java
public String divisaoMaisEconomica() {
    return this.locations.entrySet().stream()
            .min(
                (e1,e2) -> {
                    String s1 = e1.getKey(), s2 = e2.getKey();
                    Double d1 = e1.getValue().stream().mapToDouble(SmartDevice::totalConsumo).sum(),
                           d2 = e2.getValue().stream().mapToDouble(SmartDevice::totalConsumo).sum();
                    if (d1 == d2) return s1.compareTo(s2); 
                    return (int) (d1 - d2);
                }
            )
            .get()
            .getKey();
}
```

## Ex.11

```java
public class SmartBulbDimmable extends SmartBulb {
    private double intensidade;
    private double totalConsumo = 0;

    public SmartBulbDimmable(String id, int tone, double consumoPorHora) {
        super(id,tone,consumoPorHoras);
    }

    public double consumoPorHora() {return consumoPorHora * lightIntensity;}

    @Override
    public double totalConsumo() {return totalConsumo;}

    @Override
    public void turnOn() {
        this.on = true;
        if(this.intensidade == null) {
            this.intenside = 0.5;
        }
        if(this.inicio == null) {
            this.inicio = LocalDateTime.now();
        }
    }

    @Override
    public void turnOff() {
        this.on = false;
        long timeOn = Duration.between(this.inicio,LocalDateTime.now()).toHours();
        this.inicio == null;
        this.totalConsumo += timeOn * consumoPorHora();
    }


    private static class SmartBulbDimmableException extends Exception {};
    public void alteraIntensidade(Double i) throws SmartBulbDimmableException {
        if(!(i >= 0 && i <= 1)) throw new SmartBulbDimmableException();
        long timeOn = Duration.between(this.inicio,LocalDateTime.now()).toHours();
        inicio = LocalDateTime.now();
        totalConsumo += timeOn * consumoPorHora();
        lightIntensity = newLightIntesity;
    }
}
```

### Ex.12

```java 
public void alteraInfo(Consumer<SmartBulbDimmable> bd) {
    //forEach() --> tem como input um consumer 

    this.devices.stream()
                .filter(s -> s instanceof SmartBulbDimmable)
                .map(s -> (SmartBulbDimmable) s)
                .forEach(bd);
}
```
```java
Consumer<SmartBulbDimmable> bd = s -> s.alteraIntensidade(0.25);
```

### Ex.13

```java
public boolean apenasNumaDivisao() {
    return this.devices.stream()
                       .distinct()
                       .count()
                ==
           this.locations.values()
                         .stream()
                         .mapToInt(Collection::Size)
                         .sum()

}
```

### Ex.14

```java
public boolean gravaEmFichObjectos(String fich) throws FileNotFoundException, IOException {
    FileOutputStream fich = new FileOutputStream();
    ObjectOutputStream os = new ObjectOutputStream(fich);
    this.devices.stream().filter(s -> s instanceof SmartSpeaker).forEach(s -> os.writeObject(s));
    os.flush();
    os.close();
    return true;
}
```