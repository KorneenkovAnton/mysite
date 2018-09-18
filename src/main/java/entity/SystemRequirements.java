package entity;


public class SystemRequirements {
    private String operationSystem;
    private String cpuName;
    private double cpuFrequency;
    private int ram;
    private String videoAdapterName;
    private int videoAdapterMemory;
    private int freeSpace;

    public String getOperationSystem() {
        return operationSystem;
    }

    public void setOperationSystem(String operationSystem) {
        this.operationSystem = operationSystem;
    }

    public String getCpuName() {
        return cpuName;
    }

    public void setCpuName(String cpuName) {
        this.cpuName = cpuName;
    }

    public double getCpuFrequency() {
        return cpuFrequency;
    }

    public void setCpuFrequency(double cpuFrequency) {
        this.cpuFrequency = cpuFrequency;
    }

    public int getRam() {
        return ram;
    }

    public void setRam(int ram) {
        this.ram = ram;
    }

    public String getVideoAdapterName() {
        return videoAdapterName;
    }

    public void setVideoAdapterName(String videoAdapterName) {
        this.videoAdapterName = videoAdapterName;
    }

    public int getVideoAdapterMemory() {
        return videoAdapterMemory;
    }

    public void setVideoAdapterMemory(int videoAdapterMemory) {
        this.videoAdapterMemory = videoAdapterMemory;
    }

    public int getFreeSpace() {
        return freeSpace;
    }

    public void setFreeSpace(int freeSpace) {
        this.freeSpace = freeSpace;
    }

    public SystemRequirements(String operationSystem, String cpuName, double cpuFrequency, int ram,
                              String videoAdapterName, int videoAdapterMemory, int freeSpace) {
        this.operationSystem = operationSystem;
        this.cpuName = cpuName;
        this.cpuFrequency = cpuFrequency;
        this.ram = ram;
        this.videoAdapterName = videoAdapterName;
        this.videoAdapterMemory = videoAdapterMemory;
        this.freeSpace = freeSpace;
    }

    public SystemRequirements() {
    }
}
