public final class MandelbrotLogic {

    public int changeMaxIterations(final int maxIterations, final int sign) {
        return maxIterations + sign * 20;
    }

    public double[] move(final double[] positions, final int sign) {
        final double change = sign * (positions[0] - positions[1]) / 10;
        return new double[] { positions[0] + change, positions[1] + change };
    }

    public double[] zoomIn(final double[] positions) {
        final double widthChange = (positions[1] - positions[0]) / 4;
        final double heightChange = (positions[3] - positions[2]) / 4;
        return new double[] { positions[0] + widthChange,
                             positions[1] - widthChange,
                             positions[2] + heightChange,
                             positions[3] - heightChange };
    }

    public double[] zoomOut(final double[] positions) {
        final double widthChange = (positions[1] - positions[0]) / 2;
        final double heightChange = (positions[3] - positions[2]) / 2;
        return new double[] { positions[0] - widthChange,
                             positions[1] + widthChange,
                             positions[2] - heightChange,
                             positions[3] + heightChange };
    }
}
