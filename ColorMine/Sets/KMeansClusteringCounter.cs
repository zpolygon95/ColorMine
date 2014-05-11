using System;
using System.Collections.Generic;
using System.Linq;
using ColorMine.ColorSpaces;

namespace ColorMine.Sets
{
    /// <summary>
    /// http://msdn.microsoft.com/en-us/magazine/jj891054.aspx
    /// http://en.wikipedia.org/wiki/K-means_clustering
    /// http://en.wikipedia.org/wiki/Lloyd%27s_algorithm
    /// http://en.wikipedia.org/wiki/Voronoi_diagram
    /// </summary>
    /// <typeparam name="T"></typeparam>
    public class KMeansClusteringCounter<T> : ColorCounter where T:IColorSpace,new()
    {
        private int MaxClusters { get; set; }
        private int MaxIterations { get; set; }
        private IList<IColorSpace>[] Clusters { get; set; }
        private List<IColorSpace> RawData { get; set; }
        public KMeansClusteringCounter(int maxClusters, int maxIterations)
        {
            MaxClusters = maxClusters;
            MaxIterations = maxIterations;
        }

        protected override IDictionary<IColorSpace, int> CountColors(IColorMatrix matrix)
        {
            InitializeClustering(matrix);

            IColorSpace[] means = null;
            for (var i = 0; i < MaxIterations; i++)
            {
                means = ComputeClusterMeans();
                UpdateClusters(means);
                // TODO We can stop once movement has "slowed"
            }

            var result = new Dictionary<IColorSpace, int>();
            for(var clusterIndex = 0; clusterIndex < MaxClusters; clusterIndex++)
            {
                // TODO is it possible for colors to average out to invalid points?
                var count = Clusters[clusterIndex].Count;
                if(count > 0)
                {
                    var key = new T {Ordinals = means[clusterIndex].Ordinals};
                    result[key] = count;
                }
            }

            return result;
        }

        /// <summary>
        /// Data transformations and initial (random) clustering
        /// </summary>
        /// <param name="matrix"></param>
        private void InitializeClustering(IColorMatrix matrix)
        {
            var localClusters = CreateClusters();
            var localData = new List<IColorSpace>();

            // It's a fairly safe bet that closer colors are more similar.
            // We could chunk this up better as an optimization if necessary.
            for (var y = 0; y < matrix.Height; y++)
            {
                for (var x = 0; x < matrix.Width; x++)
                {
                    var clusterIndex = (x + y) % MaxClusters;
                    var color = matrix.GetColor(x, y);
                    localClusters[clusterIndex].Add(color);
                    localData.Add(color);
                }
            }

            Clusters = localClusters;
            RawData = localData;
        }

        private IColorSpace[] ComputeClusterMeans()
        {
            var result = new IColorSpace[MaxClusters];
            for (var clusterIndex = 0; clusterIndex < MaxClusters; clusterIndex++)
            {
                var clusterMean = new T();
                var size = Clusters[clusterIndex].Count;
                foreach (var color in Clusters[clusterIndex])
                {
                    clusterMean.Ordinals = clusterMean.Ordinals.Zip(color.Ordinals, (x, y) => x + (y / size)).ToArray();
                }
                result[clusterIndex] = clusterMean;
            }

            return result;
        }

        private void UpdateClusters(IColorSpace[] means)
        {
            var localCluster = CreateClusters();
            foreach (var dataPoint in RawData)
            {
                var min = double.MaxValue;
                var minIndex = 0;
                for (var clusterIndex = 0; clusterIndex < means.Length; clusterIndex++)
                {
                    var distance = GetDistance(dataPoint, means[clusterIndex]);
                    if (distance < min)
                    {
                        min = distance;
                        minIndex = clusterIndex;
                    }
                }
                localCluster[minIndex].Add(dataPoint);
            }
            Clusters = localCluster;
        }

        private IList<IColorSpace>[] CreateClusters()
        {
            var localClusters = new IList<IColorSpace>[MaxClusters];
            for (var i = 0; i < MaxClusters; i++)
            {
                localClusters[i] = new List<IColorSpace>();
            }
            return localClusters;
        }

        private static double GetDistance(IColorSpace colorA, IColorSpace colorB)
        {
            var a = colorA.Ordinals;
            var b = colorB.Ordinals;
            var size = a.Length;

            double differences = 0;
            for (var i = 0; i < size; i++)
            {
                differences += (a[i] - b[i]) *(a[i] - b[i]);
            }

            return Math.Sqrt(differences);
        }
    }
}
